package me.aktor.corsoapp.corso.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.aktor.corsoapp.corso.R;

/**
 * Created by Andrea Tortorella on 28/06/14.
 */
public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View  view  = inflater.inflate(R.layout.fragment_login,container,false);

        return view;
    }
}
