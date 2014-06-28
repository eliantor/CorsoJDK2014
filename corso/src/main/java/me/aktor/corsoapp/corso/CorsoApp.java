package me.aktor.corsoapp.corso;

import android.app.Application;

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
