package com.main.ui.signup

import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.base.BaseViewModel
import com.database.addUserToFirebase
import com.example.e_learning.R
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.model.ApplicationUser
import com.model.DataUtil

class SingUpViewModel : BaseViewModel<Navigation>() {
    var lastName = ObservableField<String>()
    var lastNameError = ObservableField<String>()
    var firstName = ObservableField<String>()
    var password = ObservableField<String>()
    var email = ObservableField<String>()
    var firstError = ObservableField<String>()
    var passwordError = ObservableField<String>()
    var emailError = ObservableField<String>()
    var phone = ObservableField<String>()
    var phoneError = ObservableField<String>()
    // sign by google
    lateinit var gso:GoogleSignInOptions
    lateinit var gsc:GoogleSignInClient


    fun validation():Boolean{
        var valid = true
        if (firstName.get().isNullOrBlank()){
            firstError.set("First Name")
            valid=false
        }else{
            firstError.set(null)
        }
        if (lastName.get().isNullOrBlank()){
            lastNameError.set("Last Name")
            valid=false
        }else{
            lastNameError.set(null)
        }
        if (email.get().isNullOrBlank()){
            emailError.set("please enter your email")
            valid=false
        }else{
            emailError.set(null)
        }
        if (password.get().isNullOrBlank()){
            passwordError.set("please enter your password")
            valid=false
        }else{
            passwordError.set(null)
        }
        if (phone.get().isNullOrBlank()){
            phoneError.set("please enter your phone")
            valid=false
        }else{
            phoneError.set(null)
        }
        return valid
    }
    val auth = Firebase.auth
    private fun createAccount() {
        showLoading.value=true
        auth.createUserWithEmailAndPassword(email.get()!!,password.get()!!)
            .addOnCompleteListener {task->
                showLoading.value=false
                when {
                    task.isSuccessful -> {
                        // this function set item in fireStore in firebase
                        createFireStoreUser(task.result.user?.uid)
                    }
                    else->{
                        messageLiveData.value=task.exception?.localizedMessage
                        Log.e("firebase","filed"+task.exception?.localizedMessage)
                    }
                }
            }
    }
    private fun createFireStoreUser(uid: String?) {
        showLoading.value=true
        val appUser = ApplicationUser(
            id = uid,
            userName = firstName.get(),
            email = email.get(),
            password = password.get(),
            phone = phone.get()

        )
        addUserToFirebase(appUser ,
            OnSuccessListener {
                showLoading.value=false
                DataUtil.user = appUser
                navigator?.openHome()
            }, OnFailureListener {
                showLoading.value=false
                messageLiveData.value=it.localizedMessage
            })
    }
    fun openHome(){
        if (validation()){
            createAccount()
        }
    }
   fun signWithGoogle(){
       navigator?.startActivityForResult()

   }

}