package com.nisoft.managertools.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * 将图片文件本地化的服务
 */
public class FileSaveService extends Service {
    public FileSaveService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
