package com.nisoft.managertools.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nisoft.managertools.R;

/**
 * Created by NewIdeaSoft on 2017/5/12.
 */

public class PhotoFragment extends Fragment{
    private static final String PHOTO_PATH = "photo_path";

    public static Fragment newInstance(String s) {
        Fragment fragment = new PhotoFragment();
        Bundle args = new Bundle();
        args.putString(PHOTO_PATH,s);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ImageView view = (ImageView) inflater.inflate(R.layout.photo_pager,container,false);
        Glide.with(getActivity()).load(getArguments().getString(PHOTO_PATH)).into(view);
        return view;
    }
}
