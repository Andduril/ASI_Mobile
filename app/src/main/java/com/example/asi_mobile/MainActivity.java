package com.example.asi_mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import com.google.firebase.firestore.DocumentReference;


public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private RecyclerView monRecyclerView;
    private List<User> userList;
    private UserAdapter monUserAdapter;
    private EditText message_saisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        monRecyclerView = findViewById(R.id.recyclerView_chat);
        message_saisi = findViewById(R.id.editText_message);

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

    public void onClickAddUser(View view) {

        String message = message_saisi.getText().toString();
        DatabaseReference usersRef = database.getReference("users");
        DatabaseReference newUserRef = usersRef.push(); // Génère une clé unique pour le nouvel utilisateur

        User newUser = new User("frodu", message);

        newUserRef.setValue(newUser)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.i("DB", "User added successfully");
                            Toast.makeText(MainActivity.this, "User added", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("DB", "Error adding user: " + task.getException());
                        }
                    }
                });
    }

    public void OnClickQuitterChat(View view) {
        finish();
    }
}