package me.aktor.corsoapp.corso;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Andrea Tortorella on 21/06/14.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    public static interface OnUpdateTextListener {
        public void onUpdateText(String text);
    }


    public static final String STATE="state";

    private TextView mText;

    private String mSavedState;

    private OnUpdateTextListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnUpdateTextListener){
            mListener = (OnUpdateTextListener)activity;
        } else {
            throw new IllegalStateException("You must implement "+OnUpdateTextListener.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        //setContentView();
        //findViewById()
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        mText =(TextView)view.findViewById(R.id.tv_output);
        view.findViewById(R.id.btn_action).setOnClickListener(this);

        if (savedInstanceState != null){
            mSavedState = savedInstanceState.getString(STATE);
        } else {
            mSavedState = mText.getText().toString();
        }

        mText.setText(mSavedState);

       return view;
    }


    public void updateText(String text) {
        mSavedState = text;
        mText.setText(mSavedState);
    }

    @Override
    public void onClick(View v) {
        mListener.onUpdateText(mSavedState);
//        MainAcitivity a= (MainAcitivity)getActivity();
//        a.startNextActivity(mSavedState);
    }







}
