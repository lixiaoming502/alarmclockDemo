package com.lixm.myapplication;

import android.app.Service;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class ClockActivity extends AppCompatActivity {


    TextView infoText;
    Button infoButton;
    Vibrator vibrator;
    MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_clock);

        //获取系统的Vibrator服务
        vibrator=(Vibrator)getSystemService(Service.VIBRATOR_SERVICE);

        infoButton = (Button)findViewById(R.id.info_button);
        infoText = (TextView)findViewById(R.id.info_desc);
        mMediaPlayer = MediaPlayer.create(this, getSystemDefultRingtoneUri());
        infoButton.setOnClickListener(new MyListener(this,vibrator,mMediaPlayer));
    }



    void vibrator(){
        //Toast.makeText(this, "触摸屏幕手机震动", Toast.LENGTH_LONG).show();
        //震动手机两秒
        vibrator.vibrate(20000);
    }

    //onstart ,onresume


    @Override
    protected void onStart() {
        Log.d("ClockActivity","on start entry");
        super.onStart();
        shake();
    }

    @Override
    protected void onResume(){
        Log.d("ClockActivity","on onResume entry");
        super.onResume();
        shake();
    }

    private void shake() {
        //接收intent传入的数据
        Bundle bundle = this.getIntent().getExtras();
        String info = bundle.getString("info");
        Log.d("info",info);
        infoText.setText(info);
        vibrator();
        //startAlarm();
    }


    private void startAlarm() {
        mMediaPlayer.setLooping(true);
        try {
            mMediaPlayer.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.start();
    }


    //获取系统默认铃声的Uri
    private Uri getSystemDefultRingtoneUri() {
        return RingtoneManager.getActualDefaultRingtoneUri(this,
                RingtoneManager.TYPE_RINGTONE);
    }


}
