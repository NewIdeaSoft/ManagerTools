package com.nisoft.managertools.adapter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nisoft.managertools.R;
import com.nisoft.managertools.entity.ProblemLab;
import com.nisoft.managertools.ui.fragment.LargePhotoFragment;
import com.nisoft.managertools.ui.fragment.ProblemDetailedInfoFragment;
import com.nisoft.managertools.ui.fragment.ProblemRecodeFragment;
import com.nisoft.managertools.ui.fragment.UpdatePhotoMenuFragment;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by NewIdeaSoft on 2017/5/2.
 */

public class ProblemPhotosAdapter extends RecyclerView.Adapter<ProblemPhotosAdapter.ViewHolder> {
    //本地文件路径
    private ArrayList<String> mPhotosPath;
    private Context mContext;
    private Fragment mTargetFragment;
    private UUID mUUID;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mProblemImage;
        public ViewHolder(View itemView) {
            super(itemView);
            mProblemImage = (ImageView) itemView;
        }
    }
    public ProblemPhotosAdapter(Context context,Fragment targetFragment,UUID uuid){
        mContext = context;
        mUUID = uuid;
        mPhotosPath = ProblemRecodeFragment.getProblem().getPhotoPath();
        mTargetFragment = targetFragment;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.problom_poto_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.mProblemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if(position == getItemCount()-1) {
                    //启动添加图片对话框（拍照或从相册选择）
                    FragmentManager manager = ((Activity)mContext).getFragmentManager();
                    UpdatePhotoMenuFragment fragment = UpdatePhotoMenuFragment.newInstance(mUUID,position);
                    fragment.setTargetFragment(mTargetFragment, ProblemDetailedInfoFragment.PICTURE_REQUEST);
//                    fragment.setTargetFragment(mTargetFragment,UpdatePhotoMenuFragment.CHOOSE_PHOTO);
                    fragment.show(manager,"update_menu");

                }else{
                    //查看大图，仿朋友圈查看大图
                    FragmentManager manager = ((Activity)mContext).getFragmentManager();
                    LargePhotoFragment imageFragment = LargePhotoFragment.newInstance(mUUID,position);
                    imageFragment.show(manager,"image");
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //获取图片路径
        if(position==getItemCount()-1) {
            Glide.with(mContext).load(R.drawable.ic_menu_add).into(holder.mProblemImage);
        }else if(position>=0){
            String photoPath = mPhotosPath.get(position);
            //通过路径加载图片
            //1.从本地加载
            Glide.with(mContext).load(photoPath).into(holder.mProblemImage);
            //2.从服务器加载
        }

    }

    @Override
    public int getItemCount() {
        if(mPhotosPath==null) {
            return 1;
        }
        return mPhotosPath.size()+1;
    }

    public void setPhotosPath(ArrayList<String> photosPath){
        mPhotosPath = photosPath;
    }
}
