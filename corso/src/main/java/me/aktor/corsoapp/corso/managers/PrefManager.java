package me.aktor.corsoapp.corso.managers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Andrea Tortorella on 28/06/14.
 */
public class PrefManager {
    public static final String X_BB_SESSION = "X-BB-SESSION";
    public static final String USER = "USER";
    public static final String MY_SUPER_KEY = "MY_SUPER_KEY";
    public static final String DONT_KNOW_WHAT_THIS_MEAN = "DONT_KNOW_WHAT_THIS_MEAN";



    private final SharedPreferences p;

    public PrefManager(Context context){
        p = context.getSharedPreferences("MY_PREFS", 0);
    }

    public void setDontKnowWhatThisMean(String value){
        p.edit().putString(DONT_KNOW_WHAT_THIS_MEAN,value).commit();
    }

    public String getSession(){
        return p.getString(X_BB_SESSION,null);
    }

    public void setSession(String session){
        p.edit().putString(X_BB_SESSION,session).commit();
    }


}
