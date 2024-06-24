package com.example.asi_mobile.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.asi_mobile.Models.User;
import com.example.asi_mobile.R;
import com.example.asi_mobile.Utils.AndroidUtils;
import com.example.asi_mobile.Utils.FirebaseUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UsernameActivity extends AppCompatActivity {
    EditText usernameInput, emailInput;
    Button connectionBtn;
    ProgressBar loginProgressBar;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_username);
        database = FirebaseDatabase.getInstance("https://asi-mobile-1bc67-default-rtdb.europe-west1.firebasedatabase.app/");
        usernameInput = findViewById(R.id.usernameInput);
        emailInput = findViewById(R.id.emailInput);
        connectionBtn = findViewById(R.id.connectionBtn);
        loginProgressBar = findViewById(R.id.loginProgressBar);
        setInProgress(false);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void OnClickConnection(View view){
        String usernameInputContent = usernameInput.getText().toString();
        String emailInputContent = emailInput.getText().toString();
        final Boolean[] userFound = {false};
        final int[] Count = {0};
        if(usernameInputContent.isEmpty() || emailInputContent.isEmpty()){
            AndroidUtils.print(this, "Please fill all fields");
        }
        else{
            setInProgress(true);
            Log.d("username", usernameInputContent);
            Log.d("email", emailInputContent);
            DatabaseReference rootRef = database.getReference();
            DatabaseReference usersRef = rootRef.child("users");
            usersRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        User user = userSnapshot.getValue(User.class);
                        Log.d("user", user.toString());
                        assert user != null;
                        if(user.getName().equals(usernameInputContent) && user.getEmail().equals(emailInputContent)){
                            Log.d("USER FOUND", "USER KEY : " + userSnapshot.getKey());
                            //AndroidUtils.print(UsernameActivity.this, "User found");
                            userFound[0] = true;
                            AndroidUtils.saveValue(UsernameActivity.this, "userKey", userSnapshot.getKey());
                            AndroidUtils.saveValue(UsernameActivity.this, "userName", usernameInputContent);
                            setInProgress(false);
                            startActivity(new Intent(UsernameActivity.this, MainActivity.class));
                            finish();
                            break;
                        }
                    }
                    if (Count[0] == 0 && !userFound[0]){ //To check later
                        Count[0]++;
                        AndroidUtils.print(UsernameActivity.this, "User not found creating new user");
                        DatabaseReference newUser = FirebaseUtils.addUser(usernameInputContent, emailInputContent);
                        Log.d("newUser", newUser.toString());
                        AndroidUtils.saveValue(UsernameActivity.this, "userKey", newUser.getKey());
                        AndroidUtils.saveValue(UsernameActivity.this, "userName", usernameInputContent);
                        setInProgress(false);
                        startActivity(new Intent(UsernameActivity.this, MainActivity.class));
                        finish();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    setInProgress(false);
                    AndroidUtils.print(UsernameActivity.this, "An error occurred");
                }
            });
        }
    }
    void setInProgress(boolean inProgress){
        if(inProgress){
            loginProgressBar.setVisibility(View.VISIBLE);
            connectionBtn.setVisibility(View.GONE);
        } else {
            loginProgressBar.setVisibility(View.GONE);
            connectionBtn.setVisibility(View.VISIBLE);
        }
    }
}