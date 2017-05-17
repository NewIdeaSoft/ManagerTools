package com.nisoft.managertools.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.nisoft.managertools.R;
import com.nisoft.managertools.db.ProblemDbSchema.ProblemTable;
import com.nisoft.managertools.engine.EditTextWather;
import com.nisoft.managertools.entity.Problem;
import com.nisoft.managertools.entity.ProblemLab;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by NewIdeaSoft on 2017/4/26.
 */

public class ProblemSimpleInfoFragment extends Fragment {
    public static final int REQUEST_CODE_DISCOVERED_DATE = 11;
    public static final int REQUEST_CODE_HANDLED_DATE = 12;
    private Problem mProblem;
    private Button mDiscoveredDate;
    private EditText mDiscover;
    private EditText mDiscoveredPosition;
    private EditText mHandler;
    private Button mHandledDate;
    private EditText mTitle;

    public static ProblemSimpleInfoFragment newInstance(UUID uuid) {
        Bundle args = new Bundle();
        args.putSerializable(ProblemTable.Cols.UUID, uuid);
        ProblemSimpleInfoFragment fragment = new ProblemSimpleInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID uuid = (UUID) getArguments().getSerializable(ProblemTable.Cols.UUID);
        mProblem = ProblemLab.getProblemLab(getActivity()).getProblem(uuid);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_problem_simple_info, container, false);
        mTitle = (EditText) view.findViewById(R.id.edit_title);
        mTitle.addTextChangedListener(new EditTextWather(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ProblemRecodeFragment.getProblem().setTitle(s.toString());
            }
        });
        mDiscoveredDate = (Button) view.findViewById(R.id.button_discovered_time);
        mDiscoveredDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(REQUEST_CODE_DISCOVERED_DATE,"选择发现时间");
            }
        });
        mDiscover = (EditText) view.findViewById(R.id.edit_discover);
        mDiscover.addTextChangedListener(new EditTextWather(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ProblemRecodeFragment.getProblem().setDiscover(s.toString());
            }
        });
        mDiscoveredPosition = (EditText) view.findViewById(R.id.edit_discover_position);
        mDiscoveredPosition.addTextChangedListener(new EditTextWather(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ProblemRecodeFragment.getProblem().setPosition(s.toString());
            }
        });
        mHandledDate = (Button) view.findViewById(R.id.button_handled_time);
        mHandledDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(REQUEST_CODE_HANDLED_DATE,"选择解决日期");
            }
        });
        mHandler = (EditText) view.findViewById(R.id.edit_handler);
        mHandler.addTextChangedListener(new EditTextWather(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ProblemRecodeFragment.getProblem().setSolver(s.toString());
            }
        });
        updateView();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode!= Activity.RESULT_OK) {
            return;
        }
        Date date = (Date) data.getSerializableExtra(DatePickerDialog.DATE_INITIALIZE);
        switch (requestCode) {
            case  REQUEST_CODE_DISCOVERED_DATE:
                ProblemRecodeFragment.getProblem().setDate(date);
//                mProblem.setDate(date);
//                SingleProblem.getSingleProblem(getActivity()).getProblem().setDate(date);
                mDiscoveredDate.setText(dateFormat(date));
                break;
            case  REQUEST_CODE_HANDLED_DATE:
                ProblemRecodeFragment.getProblem().setSolvedDate(date);
                mProblem.setSolvedDate(date);
//                SingleProblem.getSingleProblem(getActivity()).getProblem().setSolvedDate(date);
                mHandledDate.setText(dateFormat(date));
                break;
        }
    }



    @Override
    public void onPause() {
        super.onPause();
        updateProblemInfo();
//        ProblemLab.getProblemLab(getActivity()).updateProblem(mProblem);
        Log.e("TAG",mProblem.getDiscover());
        Log.e("TAG",mProblem.getDate().toString());
        Log.e("TAG",mProblem.getPosition());
        Log.e("TAG",mProblem.getSolver());
        Log.e("TAG",mProblem.getSolvedDate().toString());
        Log.e("TAG","simple");
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void updateView() {
        mProblem = ProblemRecodeFragment.getProblem();
        if(mProblem.getTitle()!=null) {
            mTitle.setText(mProblem.getTitle());
        }
        if (mProblem.getDate() != null) {
            mDiscoveredDate.setText(dateFormat(mProblem.getDate()));
        }
        if (mProblem.getDiscover() != null) {
            mDiscover.setText(mProblem.getDiscover());
        }
        if (mProblem.getPosition() != null) {
            mDiscoveredPosition.setText(mProblem.getPosition());
        }
        if (mProblem.getSolvedDate() != null) {
            mHandledDate.setText(dateFormat(mProblem.getSolvedDate()));
        }
        if (mProblem.getSolver() != null) {
            mHandler.setText(mProblem.getSolver());
        }
    }
//    private void updateView(){
//        Problem problem = SingleProblem.getSingleProblem(getActivity()).getProblem();
//        Date discoverDate = problem.getDate();
//        String position = problem.getPosition();
//        String discover = problem.getDiscover();
//        String handler = problem.getSolver();
//        Date solvedDate = problem.getSolvedDate();
//        if(discoverDate==null) {
//            discoverDate = new Date();
//            SingleProblem.getSingleProblem(getActivity()).getProblem().setDate(discoverDate);
//
//        }
//        mDiscoveredDate.setText(dateFormat(discoverDate));
//        if(position!=null) {
//            mDiscoveredPosition.setText(position);
//        }
//        if(discover!=null) {
//            mDiscover.setText(discover);
//        }
//        if(solvedDate==null) {
//            solvedDate = new Date();
//            SingleProblem.getSingleProblem(getActivity()).getProblem().setSolvedDate(solvedDate);
//
//        }
//        mHandledDate.setText(dateFormat(solvedDate));
//        mHandler.setText(handler);
//    }
    private String dateFormat(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = format.format(date);
        return dateString;
    }

    private void showDatePickerDialog(int requestCode,String title){
        FragmentManager fm = getFragmentManager();
        DatePickerDialog dialog = DatePickerDialog.newInstance(title);
        dialog.setTargetFragment(ProblemSimpleInfoFragment.this,requestCode);
        dialog.show(fm,"date");
    }

    private void updateProblemInfo(){
//        mProblem.setTitle(mTitle.getText().toString());
//        mProblem.setDiscover(mDiscover.getText().toString());
//        mProblem.setPosition(mDiscoveredPosition.getText().toString());
//        mProblem.setSolver(mHandler.getText().toString());
        ProblemRecodeFragment.getProblem().setTitle(mTitle.getText().toString());
        ProblemRecodeFragment.getProblem().setDiscover(mDiscover.getText().toString());
        ProblemRecodeFragment.getProblem().setPosition(mDiscoveredPosition.getText().toString());
        ProblemRecodeFragment.getProblem().setSolver(mHandler.getText().toString());
    }
}
