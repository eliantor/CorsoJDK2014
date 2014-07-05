package me.aktor.corsoapp.corso.network;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Andrea Tortorella on 05/07/14.
 */
public class Http {
    private static volatile Http sSelf;
    private static final Object LOCK = new Object();

    private static final String URL = "http://eliantor.github.io";

    private OkHttpClient mClient;

    protected Http(OkHttpClient client){
        mClient = client;
    }



    public static Http defaultClient(){
        if (sSelf == null){
            synchronized (LOCK){
                if (sSelf == null){
                    OkHttpClient cli = new OkHttpClient();
                    sSelf = new Http(cli);
                }
            }
        }
        return sSelf;
    }


    public String getEliantor() throws IOException {
        Request req = new Request.Builder()
                .url(URL)
                .addHeader("User-Agent","PioPio/0.1")
                .get()
                .build();
        Response response = mClient.newCall(req).execute();

        if (response.code()/100 == 2){
            String bodyString = response.body().string();
            return bodyString;
        } else {
            throw new IOException("Http Error: "+response.code());
        }
    }




}


