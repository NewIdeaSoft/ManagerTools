package com.nisoft.managertools.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.nisoft.managertools.R;
import com.nisoft.managertools.adapter.ProblemPhotosAdapter;
import com.nisoft.managertools.db.ProblemDbSchema.ProblemTable;
import com.nisoft.managertools.entity.Problem;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by NewIdeaSoft on 2017/4/26.
 */

public class ProblemDetailedInfoFragment extends Fragment {
    private RecyclerView mProblemPhotoList;
    private EditText mProblemDetailedInfo;
    private ProblemPhotosAdapter mAdapter;
    private ArrayList<String> mPhotosPath;
    private Problem mProblem;

    public static final int PICTURE_REQUEST = 0;
    private void init(){
        mProblem = ProblemRecodeFragment.getProblem();
        mPhotosPath = mProblem.getPhotoPath();
        if(mPhotosPath == null) {
            mPhotosPath = new ArrayList<>();
        }

    }
    private void initView(View view){
        mProblemPhotoList = (RecyclerView) view.findViewById(R.id.problem_photo_list);
        mProblemDetailedInfo = (EditText) view.findViewById(R.id.edit_problem_detailed_info);
        if(mProblem.getDetailedText()!=null) {
            mProblemDetailedInfo.setText(mProblem.getDetailedText());
        }
        mProblemDetailedInfo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>0) {
                    mProblem.setDetailedText(s.toString());
                    ProblemRecodeFragment.getProblem().setDetailedText(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initPhotos(){
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
        mProblemPhotoList.setLayoutManager(layoutManager);
        UUID uuid = ProblemRecodeFragment.getProblem().getUUID();
        mAdapter = new ProblemPhotosAdapter(getActivity(),ProblemDetailedInfoFragment.this,uuid);
        mProblemPhotoList.setAdapter(mAdapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_problem_detailed_info,container,false);
        initView(view);
        initPhotos();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode!= Activity.RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case PICTURE_REQUEST :
                String path = data.getStringExtra("PhotoPath");
                if(path!=null) {
                    mPhotosPath.add(path);
                }
                mAdapter.setPhotosPath(mPhotosPath);
                mProblem.setPhotoPath(mPhotosPath);
                ProblemRecodeFragment.getProblem().setPhotoPath(mPhotosPath);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
//        ProblemLab.getProblemLab(getActivity()).updateProblem(mProblem);
        Log.e("TAG", "Detailed");
    }

}
