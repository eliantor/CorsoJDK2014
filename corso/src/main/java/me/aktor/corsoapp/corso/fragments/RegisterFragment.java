package me.aktor.corsoapp.corso.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import me.aktor.corsoapp.corso.R;

/**
 * Created by Andrea Tortorella on 28/06/14.
 */
public class RegisterFragment extends Fragment {
    public static final String EMAIL_ARG = "email_arg";

    private EditText mRegEmail;

    private OnRegisterListener mListener;

    public interface OnRegisterListener {
        public void onRegister(String username,String password);
    }


    public void setOnRegisterListener(OnRegisterListener listener){
        mListener = listener;
    }

    public static RegisterFragment instance(String initialEmail){
        RegisterFragment f = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(EMAIL_ARG,initialEmail);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View  view  = inflater.inflate(R.layout.fragment_register,container,false);
        mRegEmail = (EditText)view.findViewById(R.id.in_reg_email);

        Bundle data = savedInstanceState==null? getArguments():savedInstanceState;

        mRegEmail.setText(data.getString(EMAIL_ARG,""));

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EMAIL_ARG,mRegEmail.getText().toString());
    }
}








