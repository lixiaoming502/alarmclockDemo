package com.lixm.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * 多个activity可以持有同一个服务。服务只创建一次
 * 而服务可以持有thread
 */
public class MyService extends Service {

    private static final String TAG = "MyService" ;
    public static final String ACTION = "com.lixm.myapplication.MyService";

    private final IBinder binder = new MyBinder();

    public class MyBinder extends Binder {
       /* MyService getService(){
            return MyService.this;
        }*/
    };

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG, "ServiceDemo onBind");
        return binder;
    }

    @Override
    public void onCreate() {
        Log.v(TAG, "ServiceDemo onCreate");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.v(TAG, "ServiceDemo onStart");
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG, "ServiceDemo onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }
}
