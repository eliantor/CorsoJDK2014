package me.aktor.corsoapp.corso.utils;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Andrea Tortorella on 05/07/14.
 */
public class Sleeper {
    private static final Random RAND=new Random();

    public static String sleepAndReturn(long time,TimeUnit unit,String ret){
        try {
            unit.sleep(time);
        } catch (InterruptedException e) {
            return "blocked";
        }
        return ret+RAND.nextInt();
    }
}
