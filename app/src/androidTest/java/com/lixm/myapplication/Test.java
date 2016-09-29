package com.lixm.myapplication;

import com.lixm.util.Util;

import java.io.IOException;

/**
 * Created by lixiaoming on 16/9/27.
 */
public class Test {
    public static void main(String[] args) {
        try {
            String info = Util.downloadUrl("http://104.155.30.196:8080/http_proxy/msgBox.do?msgType=GET&action=stock");
            System.out.println(info);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
