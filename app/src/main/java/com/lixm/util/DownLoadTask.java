package com.lixm.util;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

public class DownLoadTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] params) {
            try {
                Log.d("",(String) params[0]);
                String info = Util.downloadUrl("http://104.155.30.196:8080/http_proxy/msgBox.do?msgType=GET&action=stock");
                return info;
            } catch (IOException e) {
                Log.w("",e);
            }
            return "0";
        }
    }