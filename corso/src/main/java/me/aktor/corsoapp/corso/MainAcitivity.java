package me.aktor.corsoapp.corso;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Andrea Tortorella on 21/06/14.
 */
public class MainAcitivity  extends FragmentActivity
    implements MainFragment.OnUpdateTextListener,
        FragmentHellos.OnShowDetailsListener{
    private static final int REQUEST_EDIT = 1;

//    public static final String STATE = "state";
//
//    private TextView mText;
//
//
//    private final View.OnClickListener click = new View.OnClickListener(){
//        @Override
//        public void onClick(View v) {
//
//            startNextActivity();
//        }
//    };

    FragmentManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mManager =getSupportFragmentManager();
//        mText= (TextView)findViewById(R.id.tv_output);
//        View btn =findViewById(R.id.btn_action);
//        btn.setOnClickListener(click);
//
//

        // access to main application
        CorsoApp app =(CorsoApp) getApplication();

        

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_EDIT){
            if (resultCode == RESULT_OK){
                updateText(data.getStringExtra(EditActivity.RESPONSE));
            }
        }
    }

    @Override
    public void onUpdateText(String text) {
        startNextActivity(text);
    }

    @Override
    public void onShowDetails(String item) {
        showTextDetails(item);
    }

    private void updateText(String text){
        FragmentHellos hellos = (FragmentHellos) mManager.findFragmentById(R.id.ResponseList);
        if(hellos != null){
            hellos.addHello(text);
        } else {
            showTextDetails(text);
        }
    }

    private void showTextDetails(String text) {
        MainFragment f = (MainFragment) mManager.findFragmentById(R.id.FragmentMain);
        f.updateText(text);
    }

    private void  startNextActivity(String messageText){
        Intent message = new Intent(this,EditActivity.class);
        message.putExtra(EditActivity.INPUT_MESSAGE,messageText);
        //startActivity(message); // se vuole far partire una nuova schermata
        startActivityForResult(message,REQUEST_EDIT);
    }

}