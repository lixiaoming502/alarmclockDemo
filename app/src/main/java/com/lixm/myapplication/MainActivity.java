package com.lixm.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lixm.util.DownLoadTask;
import com.lixm.util.Util;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button bindBtn;
    Button startBtn;
    Button zdBtn;
    TextView timeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取系统的Vibrator服务
        alarmDemo();
        //serviceDemo();
    }

    void vibrator(){
        Toast.makeText(this, "触摸屏幕手机震动", Toast.LENGTH_LONG).show();
        //震动手机两秒
        //vibrator.vibrate(2000);
    }


    private void serviceDemo() {


        bindBtn = (Button)findViewById(R.id.bind_btn);
        startBtn = (Button)findViewById(R.id.start_btn);
        zdBtn = (Button)findViewById(R.id.zd_button);

        bindBtn.setOnClickListener(new View.OnClickListener() {

            ServiceConnection conn = new ServiceConnection() {
                public void onServiceConnected(ComponentName name, IBinder service) {
                    Log.v(TAG, "onServiceConnected");
                }
                public void onServiceDisconnected(ComponentName name) {
                    Log.v(TAG, "onServiceDisconnected");
                }
            };

            public void onClick(View v) {
                Log.v(TAG,"onClick entry");
                Intent intent = new Intent(MyService.ACTION);
                intent.setPackage(getPackageName());
                bindService(intent, conn, BIND_AUTO_CREATE);
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MyService.ACTION);
                intent.setPackage(getPackageName());
                startService(intent);
            }
        });

        zdBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //vibrator();
                Log.d("","zdBtn click");
                DownLoadTask downLoadTask = new DownLoadTask();
                downLoadTask.execute("http://104.155.30.196:8080/http_proxy/msgBox.do?msgType=GET&action=stock");
                String info ="abc";
                try {
                    info = (String) downLoadTask.get(10, TimeUnit.SECONDS);
                } catch (Exception e) {
                    info =e.getMessage();
                    e.printStackTrace();
                }
                Log.d("","zdBtn click ok");
                Toast.makeText(getApplicationContext(),"接收到["+info+"]",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void alarmDemo1() {
        //创建Intent对象，action为ELITOR_CLOCK，附加信息为字符串“你该打酱油了”
        Intent intent = new Intent("ELITOR_CLOCK");
        intent.putExtra("msg","你该打酱油了");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //定义一个PendingIntent对象，PendingIntent.getBroadcast包含了sendBroadcast的动作。
        //也就是发送了action 为"ELITOR_CLOCK"的intent
        PendingIntent pi = PendingIntent.getBroadcast(this,0,intent,0);

        //AlarmManager对象,注意这里并不是new一个对象，Alarmmanager为系统级服务
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);

        //设置闹钟从当前时间开始，每隔5s执行一次PendingIntent对象pi，注意第一个参数与第二个参数的关系
        // 5秒后通过PendingIntent pi对象发送广播
        //am.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),5*1000,pi);
        //am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);
        // am.setExact(AlarmManager.RTC_WAKEUP, 5*1000, pi);
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),5*1000, pi);
    }

    private void alarmDemo(){
        Util.setAlarmTime(getApplicationContext(),System.currentTimeMillis(),"ELITOR_CLOCK",5*1000);
    }





}
