package me.aktor.corsoapp.corso.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import me.aktor.corsoapp.corso.R;
import me.aktor.corsoapp.corso.utils.Sleeper;

/**
 * Created by Andrea Tortorella on 28/06/14.
 */
public class DynamicFragmentsActivity extends FragmentActivity implements RetainedFragment.OnResultListener {
    public static final String LOGIN_FRAGMENT = "login_fragment_tag";
    public static final String REGISTER_FRAGMENT = "register_fragment_tag";
    public static final String MEMORY = "MEMORY";

    private FragmentManager mManager;

    private TextView mResult;


    @Override
    public void onResult(String result) {
        mResult.setText(result);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_fragments);
        mResult = (TextView) findViewById(R.id.tv_result_wow);

        mManager = getSupportFragmentManager();

        if (savedInstanceState ==  null) {

            mManager.beginTransaction()
                    .add(R.id.host,new LoginFragment(),LOGIN_FRAGMENT)
                    .commit();


            initFragment();
        }

        findViewById(R.id.btn_swap).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showFragment();
                    }
                }

        );

        findViewById(R.id.btn_start_task).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sleeper.sleepAndReturn(3000, TimeUnit.MILLISECONDS,"WWWWWWWWWW");
                //startTask();
            }
        });
    }


    private void startTask(){
        RetainedFragment ret = (RetainedFragment) mManager.findFragmentByTag(MEMORY);
        ret.requestResult("WOOWWW!");
    }


    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof RegisterFragment){
            ((RegisterFragment) fragment).setOnRegisterListener(new RegisterFragment.OnRegisterListener() {
                @Override
                public void onRegister(String username, String password) {

                }
            });

        }
    }

    private void initFragment(){
        mManager.beginTransaction()
                .add(RetainedFragment.create(),MEMORY)
                .commit();
    }

    private void showFragment(){
        RegisterFragment f =RegisterFragment.instance("email@email.com");

        mManager.beginTransaction()
                .replace(R.id.host,f,REGISTER_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }

}
