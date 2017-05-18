package com.nisoft.androidlib.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/5/18.
 */

public class LeftSwipeMenuRecyclerView extends RecyclerView {
    //置顶按钮
    private TextView toTopTextView;
    //删除按钮
    private TextView deleteTextView;
    //itemxi
    public LeftSwipeMenuRecyclerView(Context context) {
        super(context);
    }

    public LeftSwipeMenuRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LeftSwipeMenuRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
