package me.aktor.corsoapp.corso.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import me.aktor.corsoapp.corso.R;
import me.aktor.corsoapp.corso.parcelables.Post;
import me.aktor.corsoapp.corso.utils.Sleeper;

/**
 * Created by Andrea Tortorella on 28/06/14.
 */
public class RetainedFragment extends Fragment {
    public static final String TAG = "LOGTAG";

    public static RetainedFragment create(){
        return new RetainedFragment();
    }

    private AsyncRandom mCurrentlyExecuting;

    private String mPendingResult;

    public static interface OnResultListener {
        public void onResult(String result);
    }

    public OnResultListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (OnResultListener)activity;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPendingResult!=null){
            mListener.onResult(mPendingResult);
            mPendingResult = null;
        }
    }

    public void requestResult(String value){
        // tpdo che facciamo se mCurrentlyExecuting != null
        if (mCurrentlyExecuting != null) return;
        AsyncRandom rand = new AsyncRandom();
        mCurrentlyExecuting = rand;
        mCurrentlyExecuting.execute(value);
    }


    private void publishResult(String res){
        if (mListener != null){
            mListener.onResult(res);
        } else {
            mPendingResult = res;
        }
        mCurrentlyExecuting = null;
    }

     private class AsyncRandom extends AsyncTask<String,Void,String>{
        Context mContext;
         AsyncRandom(){
             mContext = getActivity().getApplicationContext();
         }
        @Override
        protected String doInBackground(String... params) {
            long time = 3000;
            String string = mContext.getResources().getString(R.string.app_name);
            String s = Sleeper.sleepAndReturn(time, TimeUnit.MILLISECONDS, params[0]+string);
            return s;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d(TAG,"Return: "+s);
           publishResult(s);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCurrentlyExecuting != null){
            mCurrentlyExecuting.cancel(false);
        }
    }
}









