package com.example.asi_mobile.Utils;

import android.content.Context;
import android.widget.Toast;


public class AndroidUtils {
    public static void print(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
