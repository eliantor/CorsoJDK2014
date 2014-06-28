package me.aktor.corsoapp.corso;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Andrea Tortorella on 21/06/14.
 */
public class EditActivity extends Activity implements View.OnClickListener {
    public static final String INPUT_MESSAGE = "input_message";
    public static final String RESPONSE = "response";


    EditText mEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edit);

        mEdit = (EditText)findViewById(R.id.edt_input);
        findViewById(R.id.btn_ok).setOnClickListener(this);
        findViewById(R.id.btn_share).setOnClickListener(this);

        Intent intent = getIntent();
        String initialText = intent.getStringExtra(INPUT_MESSAGE);
        mEdit.setText(initialText);

    }

    @Override
    public void onClick(View v) {
        String value = mEdit.getText().toString();

        switch (v.getId()){
            case R.id.btn_ok:

                setResult(RESULT_OK,new Intent().putExtra(RESPONSE,value));
                finish();

                break;

            case R.id.btn_share:
                shareMessage(value);
        }
    }

    private void shareMessage(String message){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        intent.putExtra(Intent.EXTRA_SUBJECT,"Condiviso dalla mia super app");
        intent.setType("text/plain");
        Intent share = Intent.createChooser(intent, "Share");
        startActivity(share);
    }





























}
