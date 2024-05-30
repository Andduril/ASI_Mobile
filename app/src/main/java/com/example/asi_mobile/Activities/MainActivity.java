package com.example.asi_mobile.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.asi_mobile.R;
import com.example.asi_mobile.UserAdapter;
import com.example.asi_mobile.Models.User;
import com.example.asi_mobile.Utils.FirebaseUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private RecyclerView monRecyclerView;
    private List<User> userList;
    private UserAdapter monUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        monRecyclerView = findViewById(R.id.recyclerView_chat);

        userList = new ArrayList<>();
        database = FirebaseDatabase.getInstance("https://asi-mobile-1bc67-default-rtdb.europe-west1.firebasedatabase.app/");

        getDataFirebase();
        monUserAdapter = new UserAdapter(userList);
        this.monRecyclerView.setAdapter(monUserAdapter);
        this.monRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getDataFirebase() {
        DatabaseReference rootRef = database.getReference();
        DatabaseReference usersRef = rootRef.child("users");
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userList.clear();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    Log.d("elodie",user.toString());
                    userList.add(user);
                }
                monUserAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Gérez l'erreur ici
            }
        });
    }

    // TODO: create firebaseUtils.sendMessage(string content) etc...
    public void OnClickSendMessage(View view) {
        FirebaseUtils.sendMessage("Hello world", "fakeId");
    }

    public void OnClickQuitterChat(View view) {
        finish();
    }
}