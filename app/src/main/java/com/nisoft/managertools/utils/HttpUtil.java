package com.nisoft.managertools.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/6/11.
 */

public class HttpUtil {
    public static final String ADRESS_MAIN = "http://47.93.191.62:8080/InspectorToolsServer/";
    public static final String SERVLET_LOGIN = ADRESS_MAIN + "LoginServlet";
    public static final String SERVLET_UPLOAD = ADRESS_MAIN + "FileUploadServlet";
    public static final String SERVLET_MEMBER_INFO = ADRESS_MAIN + "MemberInfoServlet";
    public static final String SERVLET_MATERIAL_RECODE = HttpUtil.ADRESS_MAIN + "MaterialInspectServlet";
    public static final String SERVLET_JOBINFO = ADRESS_MAIN + "JobInfoServlet";
    public static final String SERVLET_DOWNLOAD_LAN_CHI = ADRESS_MAIN + "chi-sim";

    //本地服务器地址："http://192.168.31.189:8080/InspectorToolsServer/"

    public static void sendPostRequest(String address, RequestBody body, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).post(body).build();
        client.newBuilder().connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void sendGetRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).get().build();
        client.newBuilder().connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
