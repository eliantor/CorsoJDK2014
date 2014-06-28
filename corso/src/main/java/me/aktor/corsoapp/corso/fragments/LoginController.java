package me.aktor.corsoapp.corso.fragments;

import android.view.View;
import android.widget.EditText;

import me.aktor.corsoapp.corso.R;

/**
 * Created by Andrea Tortorella on 28/06/14.
 */
public class LoginController implements View.OnClickListener {

    public interface OnLogin{
        public void onLogin(String user,String pass);
    }

    private EditText mInEmail;
    private EditText mInPassword;

    LoginController(View view){
        view.findViewById(R.id.btn_login).setOnClickListener(this);
        // trovare view
    }

    @Override
    public void onClick(View v) {
        /// implemetare logica per recuperare dati
        /// validare dati

    }
}
