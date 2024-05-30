package com.example.asi_mobile.Activities;

import static com.example.asi_mobile.Utils.AndroidUtils.print;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.asi_mobile.R;

public class UsernameActivity extends AppCompatActivity {

    EditText usernameInput, emailInput;
    Button connectionBtn;
    ProgressBar loginProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_username);

        usernameInput = findViewById(R.id.usernameInput);
        emailInput = findViewById(R.id.emailInput);
        connectionBtn = findViewById(R.id.connectionBtn);
        loginProgressBar = findViewById(R.id.loginProgressBar);

        connectionBtn.setOnClickListener(v->{
            String usernameInputContent = usernameInput.getText().toString();
            //Test: OK
            print(getApplicationContext(),usernameInputContent);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //todelete
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(UsernameActivity.this, MainActivity.class));
                finish();
            }
        },5000);
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