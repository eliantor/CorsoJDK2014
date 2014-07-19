package me.aktor.corsoapp.corso.services;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import me.aktor.corsoapp.corso.network.BaasboxHttp;

/**
 * Created by Andrea Tortorella on 19/07/14.
 */
public class PioPioService extends IntentService {
    public static final String PIOPIOSERVICE = "PIOPIO";


    public static void startUpload(Context context,JSONObject object){
        Intent message = new Intent(context,PioPioService.class);
        message.putExtra("OPERATION","UPLOAD");
        message.putExtra("DATA",object.toString());
        context.startService(message);
    }

    public static void sync(Context context){
        Intent message = new Intent(context,PioPioService.class);
        message.putExtra("OPERATION","SYNC");
        context.startService(message);
    }

    public PioPioService() {
        super(PIOPIOSERVICE);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String op = intent.getStringExtra("OPERATION");
        if ("UPLOAD".equals(op)) {

            try {
                JSONObject data = new JSONObject(intent.getStringExtra("DATA"));
                BaasboxHttp.defaultClient().uploadDocument(data);
            } catch (JSONException e) {
                //
            } catch (IOException e) {
                //
            }
        } else if ("SYNC".equals(op)){
            sync();
        }
    }


    private void sync(){
        ContentResolver resolver = getContentResolver();
        //0 === 'local'
        Cursor query = resolver.query(Uri.parse("content://my_provider/posts"), null, "_sync = 0"/*local*/, null, null);
        // synching
        ContentValues synching = new ContentValues();
        synching.put("_sync",1/*syncing*/);
        resolver.update(Uri.parse("content://my_provider/posts"),synching,"_sync  = 0",null);
        while(query.moveToNext()) {
            long id = query.getLong(query.getColumnIndex("_id"));
            JSONObject post = postFromCursorRow(query);
            try {

                BaasboxHttp.defaultClient().uploadDocument(post);
                ContentValues updated = new ContentValues();
                updated.put("_sync",2/*synched*/);
                resolver.update(Uri.parse("content://my_provider/posts/"+id),null,null,null);
            } catch (JSONException e) {
                // error
            } catch (IOException e) {
                // error
            }

        }

        /// prendere dati
        /// caricare dati
        /// aggiornare dati
    }

    private JSONObject postFromCursorRow(Cursor query) {
        return null;
    }


}







