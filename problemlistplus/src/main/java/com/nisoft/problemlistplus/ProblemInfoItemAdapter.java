package com.nisoft.problemlistplus;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nisoft.problemlistplus.entity.Content;
import com.nisoft.problemlistplus.entity.Problem;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/19.
 */

public class ProblemInfoItemAdapter extends RecyclerView.Adapter<ProblemInfoItemAdapter.ProblemInfoItemViewHolder>{
    private Context mContext;
    private ArrayList<Content> contents;
    private static final String [] titles = {"问题详情","问题分析","处理方案"};

    public ProblemInfoItemAdapter(Problem problem, Context context) {
        this.mContext = context;
        contents = getContentsFromProblem(problem);
    }

    @Override
    public ProblemInfoItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.problem_info_item,parent,false);
        return new ProblemInfoItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProblemInfoItemViewHolder holder, int position) {
        final int witch = position;
        Content content = contents.get(position);
        holder.itemTitle.setText(content.getmTitle());
        holder.itemContent.setText(content.getmText());
        holder.itemContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                switch (witch){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        holder.itemDate.setText(content.getmDate().toString());
        holder.itemAuthor.setText(content.getmAuthor());
    }
    private ArrayList<Content> getContentsFromProblem(Problem problem){
        ArrayList<Content> contents = new ArrayList<>();
        contents.add(new Content(titles[0],problem.getDetailedText(),problem.getDate(),problem.getDiscover()));
        contents.add(new Content(titles[1],problem.getReasonText(),problem.getDate(),problem.getAnalyst()));
        contents.add(new Content(titles[2],problem.getSolvedText(),problem.getSolvedDate(),problem.getSolver()));
        return contents;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
    class ProblemInfoItemViewHolder extends RecyclerView.ViewHolder{
        TextView itemTitle;
        EditText itemContent;
        Button itemDate;
        Button itemAuthor;
        public ProblemInfoItemViewHolder(View itemView) {
            super(itemView);
            itemTitle = (TextView) itemView.findViewById(R.id.problem_info_item_title);
            itemContent = (EditText) itemView.findViewById(R.id.problem_info_item_content);
            itemDate = (Button) itemView.findViewById(R.id.problem_info_item_time);
            itemAuthor = (Button) itemView.findViewById(R.id.problem_info_item_author);
        }
    }
}
