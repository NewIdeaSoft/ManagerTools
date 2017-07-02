package com.nisoft.managertools.ui.activity;

import android.app.Fragment;

import com.nisoft.managertools.db.problem.RecodeDbSchema.RecodeTable;
import com.nisoft.managertools.ui.fragment.ProblemRecodeFragment;

public class ProblemRecodeActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        String problemId = getIntent().getStringExtra(RecodeTable.Cols.PROBLEM_ID);
        return ProblemRecodeFragment.newInstance(problemId);
    }
}
