package com.example.asi_mobile.Utils;

import android.content.Context;
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
}
