package com.nisoft.managertools.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.nisoft.managertools.R;
import com.nisoft.managertools.adapter.AnotherListAdapter;
import com.nisoft.managertools.entity.problem.ProblemDataLab;
import com.nisoft.managertools.entity.problem.ProblemRecode;
import com.nisoft.managertools.ui.activity.ProblemRecodeActivity;

import java.util.ArrayList;

/**
 * Created by NewIdeaSoft on 2017/4/25.
 */

public class ProblemListFragment extends Fragment {
    private ArrayList<ProblemRecode> mProblems;
    private AnotherListAdapter mAdapter;
    private RecyclerView mProblemsRecyclerView;
    private FloatingActionButton mNewProblemRecodeFAB;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProblems = ProblemDataLab.getProblemDataLab(getActivity()).getAllProblem();
        Log.e("length1:",mProblems.size()+"");
        mAdapter = new AnotherListAdapter(getActivity(),mProblems);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_problems_list,container,false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.problem_list_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        mProblemsRecyclerView = (RecyclerView) view.findViewById(R.id.problems_list_recyclerView);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        mProblemsRecyclerView.setLayoutManager(manager);
        mProblemsRecyclerView.setAdapter(mAdapter);
        mNewProblemRecodeFAB = (FloatingActionButton) view.findViewById(R.id.new_problem_fab);
        mNewProblemRecodeFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createProblem();
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.toolbar,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_problem_menu :
                createProblem();
                break;
            case R.id.search :

                break;
            case R.id.settings :

                break;
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mAdapter == null) {
            mAdapter = new AnotherListAdapter(getActivity(),mProblems);
            mProblemsRecyclerView.setAdapter(mAdapter);
        }else{
            ArrayList<ProblemRecode> problems = ProblemDataLab.getProblemDataLab(getActivity()).getAllProblem();
            mAdapter.setProblems(problems);
            mAdapter.notifyDataSetChanged();
        }
    }
    private void createProblem(){
        ProblemRecode problem = new ProblemRecode(getNewId());
        Intent intent = new Intent(getActivity(), ProblemRecodeActivity.class);
        ProblemDataLab.getProblemDataLab(getActivity()).addProblem(problem);
        startActivity(intent);
    }

    private String getNewId(){
        return "";
    }
}
