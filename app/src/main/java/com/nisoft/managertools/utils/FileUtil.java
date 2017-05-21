package com.nisoft.managertools.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/5/19.
 */

public class FileUtil {
    /***
     *
     * @param file 源文件
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
}
