package com.nisoft.managertools.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.nisoft.managertools.R;
import com.nisoft.managertools.db.ProblemDbSchema.ProblemTable;
import com.nisoft.managertools.engine.EditTextWather;
import com.nisoft.managertools.entity.Problem;

import java.util.UUID;

/**
 * Created by NewIdeaSoft on 2017/4/26.
 */

public class ProblemSolvedInfoFragment extends Fragment {
    private EditText mSolvedText;
    private Problem mProblem;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_problem_sovled_info,container,false);
        mSolvedText = (EditText) view.findViewById(R.id.edit_problem_solved_info);
        mSolvedText.addTextChangedListener(new EditTextWather(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ProblemRecodeFragment.getProblem().setSolvedText(s.toString());
            }
        });
        mProblem = ProblemRecodeFragment.getProblem();
        if(mProblem.getSolvedText()!=null) {
            mSolvedText.setText(mProblem.getSolvedText());
        }
        return view;
    }

    public static ProblemSolvedInfoFragment newInstance(UUID id) {
        Bundle args = new Bundle();
        args.putSerializable(ProblemTable.Cols.UUID,id);
        ProblemSolvedInfoFragment fragment = new ProblemSolvedInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onPause() {
        super.onPause();
        updateProblem();
        Log.e("TAG", "Solved");
    }

    private void updateProblem(){
        mProblem.setSolvedText(mSolvedText.getText().toString());
        ProblemRecodeFragment.getProblem().setSolvedText(mSolvedText.getText().toString());
    }
}
