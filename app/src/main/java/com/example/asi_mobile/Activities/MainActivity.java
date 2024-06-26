package com.example.asi_mobile.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asi_mobile.Models.Message;
import com.example.asi_mobile.R;
import com.example.asi_mobile.MessageAdapter;
import com.example.asi_mobile.Utils.AndroidUtils;
import com.example.asi_mobile.Utils.FirebaseUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private FirebaseDatabase database;
    private double latitude, longitude;
    private List<Message> messagesList;
    private MessageAdapter messageAdapter;
    private EditText message_saisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView monRecyclerView = findViewById(R.id.recyclerView_chat);
        message_saisi = findViewById(R.id.editText_message);
        messagesList = new ArrayList<>();
        database = FirebaseDatabase.getInstance("https://asi-mobile-1bc67-default-rtdb.europe-west1.firebasedatabase.app/");
        String userKey = AndroidUtils.getValue(this, "userKey");
        String userName = AndroidUtils.getValue(this, "userName");

        AndroidUtils.print(this, "User key: " + userKey);

        getDataFirebase();
        messageAdapter = new MessageAdapter(messagesList, userKey, new MessageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String message) {
                //AndroidUtils.print(MainActivity.this, message);
                OnClickMessageGPS(message);
            }
        });
        monRecyclerView.setAdapter(messageAdapter);
        monRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Permission de localisation
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        } else {
            this.getLocation();
        }
    }

    private void getDataFirebase() {
        DatabaseReference rootRef = database.getReference();
        DatabaseReference messagesRef = rootRef.child("messages");
        messagesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messagesList.clear();
                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                    Message message = messageSnapshot.getValue(Message.class);
                    assert message != null;
                    Log.d("perso",message.toString());
                    messagesList.add(message);
                }
                messageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                AndroidUtils.print(MainActivity.this, "Erreur de récupération des messages");

            }
        });
    }
    public void OnClickMessageGPS(String message) {
        String clickedMessage = message;
        double Longitude = 0;
        double Latitude = 0;
        //Log.d("perso", clickedMessage);
        //AndroidUtils.print(this, clickedMessage);
        DatabaseReference rootRef = database.getReference();
        DatabaseReference messagesRef = rootRef.child("messages");
        messagesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                    Message message = messageSnapshot.getValue(Message.class);
                    assert message != null;
                    if (message.getContent().equals(clickedMessage)) {
                        if (message.getIsLocation()) {
                            String[] parts = message.getContent().split(":");
                            String[] gps = parts[1].split(",");
                            longitude = Double.parseDouble(gps[0].substring(1));
                            latitude = Double.parseDouble(gps[1].substring(0, gps[1].length() - 1));
                            Log.d("perso", "Longitude: " + longitude + " Latitude: " + latitude);
                            AndroidUtils.openGoogleMap(MainActivity.this, latitude, longitude);
                        }
                        break;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                AndroidUtils.print(MainActivity.this, "Erreur de récupération du message avec les coordonnées GPS");
            }
        });
    }

    public void OnClickSendMessage(View view) {
        FirebaseUtils.sendMessage(message_saisi.getText().toString(), AndroidUtils.getValue(this, "userKey"), false, AndroidUtils.getValue(this, "userName"));
        message_saisi.setText("");
    }

    public void OnClickQuitterChat(View view) {
        finish();
    }

    public void OnClickSendLocation(View view) {
        FirebaseUtils.sendLocationMessage(longitude, latitude, AndroidUtils.getValue(this, "userKey"), AndroidUtils.getValue(this, "userName"));
    }

    public void getLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                MainActivity.this.latitude = location.getLatitude();
                MainActivity.this.longitude = location.getLongitude();
            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {
            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {
            }
        };

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission accordée, appeler getLocation()
                getLocation();
            } else {
                // Permission refusée, affichez un message à l'utilisateur
                Toast.makeText(this, "Permission refusée pour la localisation", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
