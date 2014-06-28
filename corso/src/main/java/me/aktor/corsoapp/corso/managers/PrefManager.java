package me.aktor.corsoapp.corso.managers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Andrea Tortorella on 28/06/14.
 */
public class PrefManager {

    SharedPreferences p;
    public PrefManager(Context context){
        p = context.getSharedPreferences("", 0);
    }


}
