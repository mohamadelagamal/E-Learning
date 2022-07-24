package com.main.ui.signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.base.BaseFragment
import com.example.e_learning.R
import com.example.e_learning.databinding.FragmentSignupBinding
import com.firebase.ui.auth.AuthUI.getApplicationContext
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.main.ui.home.HomeActivity

class SignUpFragment() : BaseFragment<FragmentSignupBinding,SingUpViewModel>(),Navigation {

    lateinit var mAuth:FirebaseAuth
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.vmRegister=viewModel
        viewModel.navigator=this
        mAuth = FirebaseAuth.getInstance()
        viewModel.gso =  GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        viewModel.gsc = GoogleSignIn.getClient(requireActivity(),viewModel.gso)
    }

    override fun openHome() {
      val intent = Intent(requireContext(), HomeActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_signup
    }

    override fun makeViewModelProvider(): SingUpViewModel {
        return ViewModelProvider(this).get(SingUpViewModel::class.java)
    }
    lateinit var animationDownToUp: Animation
    lateinit var animationUptoDown: Animation

    override fun startActivityForResult() {
        val signInIntent = viewModel.gsc.signInIntent
        startActivityForResult(signInIntent,1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==1000){
           val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                task.getResult(ApiException::class.java)
               // navigateToSecondActivity()
                val account : GoogleSignInAccount = task.getResult(ApiException::class.java)
               firebaseAuthWithGoogle(account)
            } catch (e:ApiException) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    fun navigateToSecondActivity() {
       activity?.finish()
        val intent = Intent(requireContext(), HomeActivity::class.java)
        startActivity(intent)
    }
    fun firebaseAuthWithGoogle(acct : GoogleSignInAccount){
        val credential : AuthCredential =
            GoogleAuthProvider.getCredential(acct.idToken,null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity(),object :OnCompleteListener<AuthResult>{
                override fun onComplete(task: Task<AuthResult>) {
                    if (task.isSuccessful){
                        val user:FirebaseUser = mAuth.currentUser!!
                    }
                    else{

                    }
                }

            })
    }
}
