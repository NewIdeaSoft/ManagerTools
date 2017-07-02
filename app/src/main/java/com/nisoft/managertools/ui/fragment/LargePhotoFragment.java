package com.nisoft.managertools.ui.fragment;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nisoft.managertools.R;
import com.nisoft.managertools.entity.problem.ProblemRecode;

import java.util.ArrayList;

/**
 * Created by NewIdeaSoft on 2017/5/12.
 */

public class LargePhotoFragment extends DialogFragment {
    private static final String SELECTED_POSITION = "selected_position";
    private ProblemRecode mProblem;
    private ArrayList<Fragment> mFragments;

    public static LargePhotoFragment newInstance(int positon){
        Bundle args = new Bundle();
        args.putInt(SELECTED_POSITION,positon);
        LargePhotoFragment fragment = new LargePhotoFragment();
        fragment.setArguments(args);
        fragment.setStyle(DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        return fragment;
    }
    public LargePhotoFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_large_photo,container);
//        ProblemRecode problem = ProblemDataLab.getProblemDataLab(getActivity()).getProblem(uuid);
//        ArrayList<String> photosPath = problem.getPhotoPath();
//        mFragments = new ArrayList<>();
//        for(int i = 0; i < photosPath.size(); i++) {
//            mFragments.add(PhotoFragment.newInstance(photosPath.get(i)));
//        }
        ViewPager pager = (ViewPager) view.findViewById(R.id.pager_large_photo);
        pager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

        });
        pager.setCurrentItem(getArguments().getInt(SELECTED_POSITION));

        return view;
    }
}
