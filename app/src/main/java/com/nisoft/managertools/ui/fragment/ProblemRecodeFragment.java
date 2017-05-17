package com.nisoft.managertools.ui.fragment;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.nisoft.managertools.R;
import com.nisoft.managertools.db.ProblemDbSchema.ProblemTable;
import com.nisoft.managertools.entity.Problem;
import com.nisoft.managertools.entity.ProblemLab;
import com.nisoft.managertools.entity.SingleProblem;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by NewIdeaSoft on 2017/4/25.
 */

public class ProblemRecodeFragment extends Fragment {
    private static Problem mProblem;
    private ImageView mProblemIcon;
    private ViewPager problemViewPager;
    private ArrayList<Fragment> problemFragmentList;
    private LinearLayout tab_problem_simple_info;
    private LinearLayout tab_problem_detailed_info;
    private LinearLayout tab_problem_reason_info;
    private LinearLayout tab_problem_solved_info;
    public static ProblemRecodeFragment newInstance(UUID uuid){
        Bundle args = new Bundle();
        args.putSerializable(ProblemTable.Cols.UUID,uuid);
        ProblemRecodeFragment fragment = new ProblemRecodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater,container,savedInstanceState);
    }
    private View initView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_problem_recode,container,false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = getActivity().getActionBar();
        if(actionBar!=null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mProblemIcon = (ImageView) view.findViewById(R.id.imageView_problem_icon);
        if(mProblem.getPhotoPath()!=null&&mProblem.getPhotoPath().size()>0) {
            Glide.with(getActivity()).load(mProblem.getPhotoPath().get(0)).into(mProblemIcon);
        }
        if(mProblem.getTitle()!=null) {
            collapsingToolbarLayout.setTitle(mProblem.getTitle());
        }else {
            collapsingToolbarLayout.setTitle("问题主题");
        }

        problemViewPager = (ViewPager) view.findViewById(R.id.problom_info_viewpager);
        FragmentManager fm = getFragmentManager();
        problemViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                return problemFragmentList.get(position);
            }
            @Override
            public int getCount() {
                return problemFragmentList.size();
            }

        });
        problemViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        resTabBackground();
                        tab_problem_simple_info.setBackgroundResource(R.color.colorTabSelect);
                        break;
                    case 1:
                        resTabBackground();
                        tab_problem_detailed_info.setBackgroundResource(R.color.colorTabSelect);
                        break;
                    case 2:
                        resTabBackground();
                        tab_problem_reason_info.setBackgroundResource(R.color.colorTabSelect);
                        break;
                    case 3:
                        resTabBackground();
                        tab_problem_solved_info.setBackgroundResource(R.color.colorTabSelect);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tab_problem_simple_info = (LinearLayout) view.findViewById(R.id.tab_problem_simple_info);
        tab_problem_detailed_info = (LinearLayout) view.findViewById(R.id.tab_problem_detailed_info);
        tab_problem_reason_info = (LinearLayout) view.findViewById(R.id.tab_problem_reason_info);
        tab_problem_solved_info = (LinearLayout) view.findViewById(R.id.tab_problem_sovled_info);

        tab_problem_simple_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resTabBackground();
                tab_problem_simple_info.setBackgroundResource(R.color.colorTabSelect);
                problemViewPager.setCurrentItem(0);
            }
        });
        tab_problem_detailed_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resTabBackground();
                tab_problem_detailed_info.setBackgroundResource(R.color.colorTabSelect);
                problemViewPager.setCurrentItem(1);
            }
        });
        tab_problem_reason_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resTabBackground();
                tab_problem_reason_info.setBackgroundResource(R.color.colorTabSelect);
                problemViewPager.setCurrentItem(2);
            }
        });
        tab_problem_solved_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resTabBackground();
                tab_problem_solved_info.setBackgroundResource(R.color.colorTabSelect);
                problemViewPager.setCurrentItem(3);
            }
        });
        return view;
    }

    private void initVariables(){
        problemFragmentList = new ArrayList<>();
        UUID uuid = (UUID) getArguments().getSerializable(ProblemTable.Cols.UUID);
        mProblem = ProblemLab.getProblemLab(getActivity()).getProblem(uuid);
        SingleProblem.getSingleProblem(getActivity()).setProblem(mProblem);
        problemFragmentList.add(ProblemSimpleInfoFragment.newInstance(uuid));
        problemFragmentList.add(ProblemDetailedInfoFragment.newInstance(uuid));
        problemFragmentList.add(ProblemReasonInfoFragment.newInstance(uuid));
        problemFragmentList.add(ProblemSolvedInfoFragment.newInstance(uuid));
    }

    private void resTabBackground(){
        tab_problem_simple_info.setBackgroundResource(R.color.colorTabBackgroung);
        tab_problem_detailed_info.setBackgroundResource(R.color.colorTabBackgroung);
        tab_problem_reason_info.setBackgroundResource(R.color.colorTabBackgroung);
        tab_problem_solved_info.setBackgroundResource(R.color.colorTabBackgroung);
    }

    @Override
    public void onPause() {
        super.onPause();
        ProblemLab.getProblemLab(getActivity()).updateProblem(getProblem());
    }

    public static Problem getProblem(){
        if(mProblem==null) {
            mProblem = new Problem();
        }
        return mProblem;
    }
}
