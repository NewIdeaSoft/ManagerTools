package com.nisoft.problemlistplus;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by NewIdeaSoft on 2017/4/25.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_content);
        if(fragment==null) {
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragment_content,fragment).commit();
        }
    }

    protected abstract Fragment createFragment();
}
