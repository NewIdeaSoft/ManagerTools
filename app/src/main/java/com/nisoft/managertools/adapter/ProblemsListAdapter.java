package com.nisoft.managertools.adapter;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nisoft.managertools.R;
import com.nisoft.managertools.db.ProblemDbSchema.ProblemTable;
import com.nisoft.managertools.entity.Problem;
import com.nisoft.managertools.ui.activity.ProblemRecodeActivity;

import java.util.ArrayList;

/**
 * Created by NewIdeaSoft on 2017/5/6.
 */

public class ProblemsListAdapter extends RecyclerView.Adapter<ProblemsListAdapter.ViewHolder>{

    private Context mContext;
    private Fragment mFragment;
    private ArrayList<Problem> mProblems;

    public ProblemsListAdapter(Fragment fragment, ArrayList<Problem> problems) {
        mFragment = fragment;
        mContext = fragment.getActivity();
        mProblems = problems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.problems_list_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProblemRecodeActivity.class);
                int position = holder.getAdapterPosition();
                Log.e("POSITION:",position+"");
                intent.putExtra(ProblemTable.Cols.UUID,mProblems.get(position).getUUID());
                mFragment.startActivity(intent);

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Problem problem = mProblems.get(position);
        if(problem.getPhotoPath().size()>0) {

            Glide.with(mContext).load(problem.getPhotoPath().get(0)).into(holder.mPhotoImageView);
        }
        holder.mTextViewTitle.setText(problem.getTitle());
        holder.mTextViewSimpleInfo.setText(problem.getDetailedText());
    }

    @Override
    public int getItemCount() {
        return mProblems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView mCardView;
        ImageView mPhotoImageView;
        TextView mTextViewTitle;
        TextView mTextViewSimpleInfo;

        public ViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView;
            mPhotoImageView = (ImageView) itemView.findViewById(R.id.problem_first_picture);
            mTextViewTitle = (TextView) itemView.findViewById(R.id.text_problem_title);
            mTextViewSimpleInfo = (TextView) itemView.findViewById(R.id.text_problem_simple_info);
        }
    }
    public void setProblems(ArrayList<Problem> problems){
        mProblems = problems;
    }
}
