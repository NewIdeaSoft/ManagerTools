package com.nisoft.managertools.ui.activity;

import android.app.Fragment;

import com.nisoft.managertools.db.ProblemDbSchema.ProblemTable;
import com.nisoft.managertools.ui.fragment.ProblemRecodeFragment;

import java.util.UUID;

public class ProblemRecodeActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        UUID uuid = (UUID) getIntent().getSerializableExtra(ProblemTable.Cols.UUID);
        return ProblemRecodeFragment.newInstance(uuid);
    }
}
