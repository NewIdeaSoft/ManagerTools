package com.nisoft.problemlistplus;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nisoft.problemlistplus.entity.Problem;

/**
 * Created by Administrator on 2017/5/19.
 */

public class ProblemInfoItemFragment extends Fragment {
    private RecyclerView mProblemInfoRecycler;
    private ProblemInfoItemAdapter mAdapter;
    public static ProblemInfoItemFragment newInstance(Problem problem){
        Bundle args = new Bundle();
        args.putSerializable("problem",problem);
        ProblemInfoItemFragment fragment = new ProblemInfoItemFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.single_problem_info,container,false);
        mProblemInfoRecycler = (RecyclerView) view.findViewById(R.id.problem_info_recycler);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        mProblemInfoRecycler.setLayoutManager(manager);
        Problem problem = (Problem) getArguments().getSerializable("problem");
        mAdapter = new ProblemInfoItemAdapter(problem,getActivity());
        mProblemInfoRecycler.setAdapter(mAdapter);
        return view;
    }
}
