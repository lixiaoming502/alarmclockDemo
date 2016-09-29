package com.lixm.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.lixm.util.DownLoadTask;
import com.lixm.util.Util;

import java.util.concurrent.TimeUnit;

public class MyReceiver extends BroadcastReceiver {

    String msg = "";
    public MyReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //因为setWindow只执行一次，所以要重新定义闹钟实现循环。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Util.setAlarmTime(context, System.currentTimeMillis() + 5*1000, "ELITOR_CLOCK", 5*1000);
        }
        Log.d("MyReceiver", "onclock......................");
        msg = msg+"|"+Util.getCurrentTime();
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
        try {
            DownLoadTask downLoadTask = new DownLoadTask();
            downLoadTask.execute("http://104.155.30.196:8080/http_proxy/msgBox.do?msgType=GET&action=stock");
            String info = (String) downLoadTask.get(10, TimeUnit.SECONDS);
            info = info.trim();
            Log.d("MyReceiver","info ["+info+"]");
            if(!info.equals("0")){
                Intent clock = new Intent(context,ClockActivity.class);
                clock.putExtra("info",info);
                clock.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(clock);
            }
        } catch (Exception e) {
            Toast.makeText(context,"Err:"+e.getMessage(),Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }



}
