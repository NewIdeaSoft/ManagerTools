package com.nisoft.managertools.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nisoft.managertools.R;
import com.nisoft.managertools.db.problem.RecodeDbSchema;
import com.nisoft.managertools.entity.problem.ProblemDataLab;
import com.nisoft.managertools.entity.problem.ProblemRecode;
import com.nisoft.managertools.ui.activity.ProblemListActivity;
import com.nisoft.managertools.ui.activity.ProblemRecodeActivity;
import com.nisoft.managertools.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/18.
 */

public class SwipeLeftDeleteAdapter extends RecyclerView.Adapter<SwipeLeftDeleteAdapter.SwipeLeftViewHolder> {
    private Context mContext;
    private ProblemRecode mProblem;
    public SwipeLeftDeleteAdapter(Context context, ProblemRecode problem){
        mContext = context;
        mProblem = problem;
    }
    @Override
    public SwipeLeftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.problems_list_item,parent,false);
        return new SwipeLeftViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SwipeLeftViewHolder holder, int position) {
        if (position == 0){
            String folderPath = mProblem.getImagesFolderPath();
            ArrayList<String> pathList = FileUtil.getImagesPath(folderPath);

            if (pathList!=null&&pathList.size()>0){
                Glide.with(mContext).load(pathList.get(0)).into(holder.mProblemImageView);
            }
            if (mProblem.getTitle()!=null){
                holder.mProblemTitle.setText(mProblem.getTitle());
            }
            if (mProblem.getDescription()!=null){
                holder.mProblemDetailedInfo.setText(mProblem.getDescription());
            }

            holder.mParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ProblemRecodeActivity.class);
                    intent.putExtra(RecodeDbSchema.RecodeTable.Cols.PROBLEM_ID,mProblem.getRecodeId());
                    mContext.startActivity(intent);
                }
            });

        }else if (position == 1){
            holder.mCardView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));

            holder.mParent.removeAllViews();
            TextView textView = (TextView) LayoutInflater.from(mContext).inflate(R.layout.delete_problem,holder.mParent,false);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String folderPath = mProblem.getImagesFolderPath();
                    ArrayList<String> pathList = FileUtil.getImagesPath(folderPath);
                    ProblemDataLab.getProblemDataLab(mContext.getApplicationContext()).delete(mProblem);
                    //删除照片文件，添加从相册复制文件到应用图片存储文件后启用，以免删除相册图片

                    if (pathList!=null&&pathList.size()>0){
                        for (int i = 0;i<pathList.size();i++){
                            File file = new File(pathList.get(i));
                            file.delete();
                        }
                    }
                    (((ProblemListActivity)mContext).getFragmentManager().findFragmentById(R.id.fragment_content)).onResume();
                }
            });
            holder.mParent.addView(textView);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    static class SwipeLeftViewHolder extends RecyclerView.ViewHolder{
        LinearLayout mParent;
        CardView mCardView;
        ImageView mProblemImageView;
        TextView mProblemTitle;
        TextView mProblemDetailedInfo;
        public SwipeLeftViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView;
            mParent = (LinearLayout) itemView.findViewById(R.id.item_parent_view);
            mProblemImageView = (ImageView) itemView.findViewById(R.id.problem_first_picture);
            mProblemTitle = (TextView) itemView.findViewById(R.id.text_problem_title);
            mProblemDetailedInfo = (TextView) itemView.findViewById(R.id.text_problem_simple_info);
        }
    }
}
