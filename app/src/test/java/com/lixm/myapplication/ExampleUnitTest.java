package com.lixm.myapplication;

import com.lixm.util.Util;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testDownload() throws Exception{
        try {
            String info = Util.downloadUrl("http://104.155.30.196:8080/http_proxy/msgBox.do?msgType=GET&action=stock");
            System.out.println(info);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}