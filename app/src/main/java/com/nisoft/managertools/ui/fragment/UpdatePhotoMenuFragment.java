package com.nisoft.managertools.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.nisoft.managertools.R;
import com.nisoft.managertools.db.ProblemDbSchema.ProblemTable;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by NewIdeaSoft on 2017/5/3.
 */

public class UpdatePhotoMenuFragment extends DialogFragment {
    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;
    public static String IMAGE_PATH="/godeyes/";
    public static String IMAGE_POSITION = "image_position";

    public static UpdatePhotoMenuFragment newInstance(UUID uuid,int position) {
        Log.e("UUID:",uuid.toString());
        Bundle args = new Bundle();
        args.putSerializable(ProblemTable.Cols.UUID,uuid);
        args.putInt(IMAGE_POSITION,position);
        UpdatePhotoMenuFragment fragment = new UpdatePhotoMenuFragment();
        fragment.setArguments(args);
        fragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        return fragment;
    }

    private Button mMakePhoto;
    private Button mChoosePhoto;
    private String path;
    private UUID uuid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, container);
        uuid = (UUID) getArguments().getSerializable(ProblemTable.Cols.UUID);
        mMakePhoto = (Button) view.findViewById(R.id.make_picture);
        mChoosePhoto = (Button) view.findViewById(R.id.choose_picture);

        mMakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    openCamera();
                }

            }
        });
        mChoosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否已经获得权限，有则打开相册，没有则申请权限
                if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    openAlbum();
                }
            }
        });
        return view;
    }

    //重写处理运行时权限申请结果的回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1 :
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                }else {
                    Toast.makeText(getActivity(), "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
    private void openCamera(){
        //调用系统相机拍摄照片
        //照片文件存储路径
        Uri uri = null;
        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+IMAGE_PATH);
        if(!dir.exists()) {
            dir.mkdir();
        }
        int position = -1;
        position = getArguments().getInt(IMAGE_POSITION);
        Log.e(IMAGE_POSITION,position+"");
        File outputImage = new File(dir, uuid.toString()+"_" + position +".jpg");
        path = null;
        if (outputImage.exists()) {
            outputImage.delete();
        }
        try {
            outputImage.createNewFile();
            path = outputImage.getAbsolutePath();
            Log.d("PhotoPath",path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(getActivity(), "com.newideasoft.godeyes.fileprovider", outputImage);
        } else {
            uri = Uri.fromFile(outputImage);
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, TAKE_PHOTO);
    }

    /**
     * 打开相册
     */
    private void openAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode!= Activity.RESULT_OK) {
            return;
        }

        if (getTargetFragment() == null) {
            return;
        }
        String photoPath = null;
        switch (requestCode) {
            case TAKE_PHOTO :
                photoPath = path;
                break;
            case CHOOSE_PHOTO:
                Uri uri = data.getData();
                if(DocumentsContract.isDocumentUri(getActivity(),uri)) {
                    String docId = DocumentsContract.getDocumentId(uri);
                    if("com.android.providers.media.documents".equals(uri.getAuthority())) {
                        String id = docId.split(":")[1];
                        String selection = MediaStore.Images.Media._ID+"="+id;
                        photoPath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
                    }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                        Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                        photoPath = getImagePath(contentUri,null);
                    }
                }else if("content".equalsIgnoreCase(uri.getScheme())) {
                    photoPath = getImagePath(uri,null);
                }else if("file".equalsIgnoreCase(uri.getScheme())) {
                    photoPath = uri.getPath();
                }
        }
        Intent i = new Intent();
        i.putExtra("PhotoPath",photoPath);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
        this.dismiss();
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getActivity().getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null) {
            if(cursor.moveToFirst()) {
                path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }

        return path;
    }
}
