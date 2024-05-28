package com.example.asi_mobile.utils;


import com.example.asi_mobile.model.UserModel;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;



public class FirebaseUtilities {
    //todo
    //////////////////////////////USER_MODEL/////////////////////////////
    // add un user
    // get un user grace à la combi (username,email) s'il n'existe pas return null

    public static DocumentReference addUser(UserModel user){
        DocumentReference userRef = FirebaseFirestore.getInstance().collection("users").document(user.getUsername());
        userRef.set(user);
        return userRef;
    }

    public static UserModel getUser(String username, String email){

        return null;
    }
    ////////////////////////////////////////////////////////////////////


    //////////////////////////////MESSAGE_MODEL/////////////////////////////

}
