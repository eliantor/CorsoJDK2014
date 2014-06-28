package me.aktor.corsoapp.corso.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import me.aktor.corsoapp.corso.parcelables.Post;

/**
 * Created by Andrea Tortorella on 28/06/14.
 */
public class RetainedFragment extends Fragment {

    private List<Post> myData;

    public static RetainedFragment create(){
        return new RetainedFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        myData = new ArrayList<Post>();
    }




}
