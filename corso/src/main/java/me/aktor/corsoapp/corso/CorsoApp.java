package me.aktor.corsoapp.corso;

import android.app.Application;
import android.os.StrictMode;

import java.util.concurrent.atomic.AtomicInteger;

import me.aktor.corsoapp.corso.managers.PrefManager;

/**
 * Created by Andrea Tortorella on 28/06/14.
 */
public class CorsoApp extends Application {

    private final AtomicInteger val = new AtomicInteger(0);

    private PrefManager prefs;


    private static CorsoApp self;


    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyDeath().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyDeath().build());
        }
        self = this;
        prefs = new PrefManager(this);
    }



    public static CorsoApp get(){
        return self;
    }

    public PrefManager getPref(){
        return prefs;
    }

    public int increment(){
        return val.getAndIncrement();
    }

}









