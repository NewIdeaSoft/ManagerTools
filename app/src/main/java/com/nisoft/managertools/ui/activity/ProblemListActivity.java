package com.nisoft.managertools.ui.activity;

import android.app.Fragment;

import com.nisoft.managertools.ui.fragment.ProblemListFragment;

public class ProblemListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ProblemListFragment();
    }
}
