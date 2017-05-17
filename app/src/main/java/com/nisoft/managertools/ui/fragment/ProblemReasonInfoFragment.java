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

public class ProblemReasonInfoFragment extends Fragment {
    private EditText reasonText;
    private Problem mProblem;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_problem_reason_info,container,false);
        reasonText = (EditText) view.findViewById(R.id.edit_problem_reason_info);
        reasonText.addTextChangedListener(new EditTextWather(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ProblemRecodeFragment.getProblem().setReasonText(s.toString());
            }
        });
//        mProblem = ProblemLab.getProblemLab(getActivity()).getProblem((UUID) getArguments().getSerializable(ProblemTable.Cols.UUID));
        mProblem = ProblemRecodeFragment.getProblem();
        if(mProblem.getReasonText()!=null) {
            reasonText.setText(mProblem.getReasonText());
        }
        return view;
    }

    public static ProblemReasonInfoFragment newInstance(UUID id) {
        Bundle args = new Bundle();
        args.putSerializable(ProblemTable.Cols.UUID,id);
        ProblemReasonInfoFragment fragment = new ProblemReasonInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onPause() {
        super.onPause();
        updateProblem();
//        ProblemLab.getProblemLab(getActivity()).updateProblem(mProblem);
        Log.e("TAG", "Reason");
    }

    private void updateProblem(){
        mProblem.setReasonText(reasonText.getText().toString());
        ProblemRecodeFragment.getProblem().setReasonText(reasonText.getText().toString());
    }
}
