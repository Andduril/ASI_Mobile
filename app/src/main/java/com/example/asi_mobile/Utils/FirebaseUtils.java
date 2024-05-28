package com.example.asi_mobile.Utils;


import com.example.asi_mobile.Models.User;
import com.google.firebase.Timestamp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;


public class FirebaseUtils {
    private static FirebaseDatabase database = FirebaseDatabase.getInstance("https://asi-mobile-1bc67-default-rtdb.europe-west1.firebasedatabase.app/");

    //todo
    //////////////////////////////USER_MODEL/////////////////////////////
    // add un user
    // get un user grace à la combi (username,email) s'il n'existe pas return null

    public static DatabaseReference addUser(String name, String email){
        DatabaseReference usersRef = database.getReference().child("users");
        DatabaseReference newUserRef = usersRef.push();
        User newUser = new User(name, email, Timestamp.now());
        newUserRef.setValue(newUser);
        return newUserRef;
    }

    public static User getUser(String username, String email){
        DatabaseReference usersRef = database.getReference().child("users");
        DataSnapshot dataSnapshot = usersRef.get().getResult();
        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
            User user = userSnapshot.getValue(User.class);
            if(user.getUsername().equals(username) && user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }
    ////////////////////////////////////////////////////////////////////


    //////////////////////////////MESSAGE_MODEL/////////////////////////////

}