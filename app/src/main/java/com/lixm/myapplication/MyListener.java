package com.lixm.myapplication;

import android.media.MediaPlayer;
import android.os.Vibrator;
import android.view.View;

/**
 * Created by lixiaoming on 16/9/27.
 */
public class MyListener implements View.OnClickListener {
    ClockActivity clockActivity;
    Vibrator vibrator;
    MediaPlayer mMediaPlayer;
    MyListener(ClockActivity clockActivity, Vibrator vibrator, MediaPlayer mMediaPlayer){
        this.clockActivity = clockActivity;
        this.vibrator = vibrator;
        this.mMediaPlayer = mMediaPlayer;
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.info_button){
            vibrator.cancel();
            //mMediaPlayer.stop();
            clockActivity.finish();
        }
    }
}
