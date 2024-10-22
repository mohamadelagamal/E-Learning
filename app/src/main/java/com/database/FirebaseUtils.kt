package com.database

import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.model.ApplicationUser

fun createCollection(collectionName:String):CollectionReference{
    val database = Firebase.firestore
    return database.collection((collectionName))
}
fun addUserToFirebase(user:ApplicationUser , onSuccessListener: OnSuccessListener<Void>,
                      onFailureListener: OnFailureListener){
    //val db = Firebase.firestore
    val userCollection = createCollection(ApplicationUser.COLLECTION_NAME)
    val userDoc =  userCollection.document(user.id!!)
    userDoc.set(user).
    addOnSuccessListener (onSuccessListener)
        .addOnFailureListener(onFailureListener)
}
// check user in firebase
fun getUser(uid:String,onSuccessListener: OnSuccessListener<DocumentSnapshot>,
            onFailureListener: OnFailureListener){
    val collectionREF = createCollection(ApplicationUser.COLLECTION_NAME)
    collectionREF.document(uid).get().addOnSuccessListener(onSuccessListener).addOnFailureListener(onFailureListener)
}

fun getCollection(collectionName:String):CollectionReference{
    val db = Firebase.firestore
    // val collectionRef = db.collection(ApplicationUser.COLLECTION_NAME)
    return db.collection(collectionName)
}

fun addUserToFireStore(user:ApplicationUser  , onSuccessListener: OnSuccessListener<Void>, onFailureListener: OnFailureListener){
    //val db = Firebase.firestore
    val userCollection = getCollection(ApplicationUser.COLLECTION_NAME)
    val userDoc =  userCollection.document(user.id!!)
    userDoc.set(user).
    addOnSuccessListener (onSuccessListener)
        .addOnFailureListener(onFailureListener)
}

