package com.nisoft.problemlistplus;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nisoft.problemlistplus.db.ProblemDbSchema.ProblemTable;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by NewIdeaSoft on 2017/5/13.
 */

public class ProblemInfoFragment extends Fragment {
    private Toolbar mToolbar;
    private ImageView mProblemIcon;
    private ViewPager mViewPager;
    private ArrayList<Fragment> mFragmentArrayList;

    public static ProblemInfoFragment newInstance(UUID uuid){
        Bundle args = new Bundle();
        args.putSerializable(ProblemTable.Cols.UUID,uuid);
        ProblemInfoFragment fragment = new ProblemInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_problem_info,container,false);
        initView(view);

        return view;
    }
    private void initView(View view){
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar_new);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar_new);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        mProblemIcon = (ImageView) collapsingToolbarLayout.findViewById(R.id.imageView_problem_photo);
        ActionBar actionBar =  getActivity().getActionBar();
        if(actionBar!=null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mViewPager = (ViewPager) view.findViewById(R.id.problem_info_viewpager_new);

    }
}
