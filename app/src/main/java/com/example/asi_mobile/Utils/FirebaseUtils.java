package com.example.asi_mobile.Utils;

import com.example.asi_mobile.Models.Message;
import com.example.asi_mobile.Models.User;

import android.util.Log;

import com.google.firebase.Timestamp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

public class FirebaseUtils {
    private static final FirebaseDatabase database = FirebaseDatabase.getInstance("https://asi-mobile-1bc67-default-rtdb.europe-west1.firebasedatabase.app/");

    // accès au dossier messages de la bdd
    private static final DatabaseReference messagesAccessor = database.getReference("messages");

    //todo
    //////////////////////////////USER_MODEL/////////////////////////////
    // add un user
    // get un user grace à la combi (username,email) s'il n'existe pas return null

    public static DatabaseReference addUser(String name, String email){
        DatabaseReference usersRef = database.getReference().child("users");
        DatabaseReference newUserRef = usersRef.push();
        User newUser = new User(name, email);
        newUserRef.setValue(newUser);
        return newUserRef;
    }

    public static User getUser(String username, String email){
        DatabaseReference usersRef = database.getReference().child("users");
        DataSnapshot dataSnapshot = usersRef.get().getResult();
        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
            User user = userSnapshot.getValue(User.class);
            if(user.getName().equals(username) && user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }
    ////////////////////////////////////////////////////////////////////


    //////////////////////////////MESSAGE_MODEL/////////////////////////////
    // sendMessages
    public static void sendMessage(String content, String userId, Boolean isLocation) {
        Message newMessage = new Message(content, userId, isLocation);
        DatabaseReference newMessageRef = messagesAccessor.push();

        newMessageRef.setValue(newMessage)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.i("MessageAccessor", "Message sent");
                    } else {
                        Log.e("MessageAccessor", "Error messages :" + task.getException());
                    }
                });
    }

    public static void sendLocationMessage(Double lon, Double lat, String userId)
    {
        if (lon != null && lat != null) {
            String content = "GPS:(" + lon + "," + lat+ ")";
            FirebaseUtils.sendMessage(content, userId, true);
        } else {
            Log.i("Location", "failed");
        }
    }
}