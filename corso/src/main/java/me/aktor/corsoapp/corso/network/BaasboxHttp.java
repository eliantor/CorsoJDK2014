package me.aktor.corsoapp.corso.network;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Andrea Tortorella on 05/07/14.
 */
public class BaasboxHttp {
    private static volatile BaasboxHttp sSelf;
    private static final Object LOCK = new Object();

    public static final String URL = "";

    public static final String APPCODE = "1234567890";
    public static final String APPCODE_KEY = "X-BAASBOX-APPCODE";

    public static final MediaType JSON =
            MediaType.parse("application/json;charset=utf-8");

    private OkHttpClient mClient;

    protected BaasboxHttp(OkHttpClient client){
        mClient = client;
    }



    public static BaasboxHttp defaultClient(){
        if (sSelf == null){
            synchronized (LOCK){
                if (sSelf == null){
                    OkHttpClient cli = new OkHttpClient();
                    sSelf = new BaasboxHttp(cli);
                }
            }
        }
        return sSelf;
    }

    public static final String COLLECTION= "posts";

    public JSONObject uploadDocument(JSONObject object) throws JSONException,IOException {
        RequestBody jsonBody = RequestBody.create(JSON,object.toString());
        Request req = new Request.Builder()
                        .url(URL+"/documents/"+COLLECTION)
                        .post(jsonBody)
                        .build();
        Response r = mClient.newCall(req).execute();
        String string = r.body().string();
        JSONObject response = new JSONObject(string);
        return response;
    }


    public JSONObject signup(String username,String password) throws JSONException, IOException {
        JSONObject body = new JSONObject();
        body.put("username", username);
        body.put("password",password);
        RequestBody jsonBody = RequestBody.create(JSON,body.toString());
        Request req = new Request.Builder()
                    .url(URL+"/user")
                    .addHeader(APPCODE_KEY,APPCODE)
                    .post(jsonBody)
                    .build();

        Response resp = mClient.newCall(req).execute();
        //todo read response
        return null;
    }
}


