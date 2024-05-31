package com.example.asi_mobile.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;


public class AndroidUtils {
    public static void print(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

   public static void saveValue(Context context, String key, String value){
        context.getSharedPreferences("asi_mobile", Context.MODE_PRIVATE).edit().putString(key, value).apply();
    }

    public static String getValue(Context context, String key){
        return context.getSharedPreferences("asi_mobile", Context.MODE_PRIVATE).getString(key, null);
    }

    public static void openGoogleMap(Context context, double latitude, double longitude){
        String uri = "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude;
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
    }
}
