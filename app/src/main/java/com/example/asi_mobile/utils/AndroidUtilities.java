package com.example.asi_mobile.utils;

import android.content.Context;
import android.widget.Toast;


public class AndroidUtilities {
    public static void print(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
