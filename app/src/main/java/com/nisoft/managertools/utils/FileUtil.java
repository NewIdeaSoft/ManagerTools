package com.nisoft.managertools.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/19.
 */

public class FileUtil {
    /***
     *
     * @param resPath 源文件
     * @param targetPath 目标路径
     */
    public static void copyFile(String resPath,String targetPath){
        try {
            File file = new File(resPath);
            if (file.exists()){
                int byteSum = 0;
                int byteRead = 0;
                InputStream in = new FileInputStream(file);
                OutputStream out = new FileOutputStream(targetPath);
                byte[] buffer = new byte[1024];
                int length;
                while((byteRead = in.read(buffer))!=-1){
                    byteSum += byteRead;
                    out.write(buffer,0,byteRead);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void moveFile(String photoPath, String targetPhotoPath) {

    }

    public static ArrayList<String> getImagesPath(String folderPath){
        File file = new File(folderPath);
        if(!file.exists()) {
            file.mkdirs();
        }
        String [] files = file.list();
        ArrayList<String> pathList = null;
        if(files!=null&&files.length>0) {
            pathList = new ArrayList<>();
            for (String fileName : files) {
                if(fileName.endsWith("jpg")||fileName.endsWith("bmp")||fileName.endsWith("png")) {
                    pathList.add(folderPath+"/"+fileName);
                }
            }
        }
        return pathList;
    }
}
