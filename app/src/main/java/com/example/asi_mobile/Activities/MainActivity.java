package com.example.asi_mobile.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asi_mobile.Models.Message;
import com.example.asi_mobile.R;
import com.example.asi_mobile.MessageAdapter;
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
    private List<Message> messagesList;
    private MessageAdapter messageAdapter;
    private EditText message_saisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        monRecyclerView = findViewById(R.id.recyclerView_chat);
        message_saisi = findViewById(R.id.editText_message);

        messagesList = new ArrayList<>();
        database = FirebaseDatabase.getInstance("https://asi-mobile-1bc67-default-rtdb.europe-west1.firebasedatabase.app/");

        getDataFirebase();
        messageAdapter = new MessageAdapter(messagesList);
        this.monRecyclerView.setAdapter(messageAdapter);
        this.monRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getDataFirebase() {
        DatabaseReference rootRef = database.getReference();
        DatabaseReference messagesRef = rootRef.child("messages");
        messagesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                messagesList.clear();
                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                    Message message = messageSnapshot.getValue(Message.class);
                    Log.d("perso",message.toString());
                    messagesList.add(message);
                }
                messageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Gérez l'erreur ici
            }
        });
    }

    public void OnClickSendMessage(View view) {
        FirebaseUtils.sendMessage(message_saisi.getText().toString(), "fakeId");
    }

    public void OnClickQuitterChat(View view) {
        finish();
    }
}