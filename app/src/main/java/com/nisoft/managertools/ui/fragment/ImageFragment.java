package com.nisoft.managertools.ui.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by NewIdeaSoft on 2017/5/3.
 */

public class ImageFragment extends DialogFragment {
    public static final String EXTRA_IMAGE_PATH = "com.newideasoft.godeyes.image_path";

    private ImageView mImageView;
    public static ImageFragment newInstance(String imagePath){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_IMAGE_PATH,imagePath);
        ImageFragment imageFragment = new ImageFragment();
        imageFragment.setArguments(args);
        imageFragment.setStyle(DialogFragment.STYLE_NO_TITLE,0);
        return imageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mImageView = new ImageView(getActivity());
        String path = (String) getArguments().getSerializable(EXTRA_IMAGE_PATH);
        Glide.with(getActivity()).load(path).into(mImageView);
        return mImageView;
    }
}
