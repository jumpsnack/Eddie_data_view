package com.example.jumpsnack.eddie_data_view;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    /* Constants Var */
    private final String JSON_FILE_NAME = "sensordata2.json";

    ViewPager viewPager;
    PageAdapter adapter;

    /* Reference Var */
    InputStream jsonInputStream;

    /*
   Handler
    */
    MessageHandler handler = null;

    /*
    Thread
     */
    DataThread thread = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        handler = new MessageHandler();
        handler = MessageHandler.getInstance();

        viewPager = (ViewPager) findViewById(R.id.vp_main_ground);

        adapter = new PageAdapter(this, getJsonFile());

        viewPager.setAdapter(adapter);


        thread = new DataThread();
        thread.start();
//        setActionBarTitle(JsonController.jsonDataSensorInfo);
    }

    /* Get JSON file */
    public String getJsonFile() {
        String jsonContents = null; /* Variable for saving original JSON converted to String */

        /* Read JSON file from "assets" DIR */
        try {
            jsonInputStream = getAssets().open(JSON_FILE_NAME);
            int size = jsonInputStream.available();
            byte[] buffer = new byte[size];
            jsonInputStream.read(buffer);
            jsonInputStream.close();
            jsonContents = new String(buffer, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonContents;
    }

    class DataThread extends Thread {
        boolean flagThrad = false;

        public DataThread() {
            flagThrad = true;
        }

        public void stopThread() {
            flagThrad = false;
        }

        public void restartThread() {
            flagThrad = true;
            start();
        }

        @Override
        public void run() {
            super.run();
            while (flagThrad) {

                Message msg = handler.obtainMessage();
                msg.arg1 = MessageHandler.MSG_DATA_AIR_POLUTION;
                msg.obj = adapter.getRandomData();
                handler.sendMessage(msg);

                Message msg1 = handler.obtainMessage();
                msg1.arg1 = MessageHandler.MSG_DATA_HEART_RATE;
                msg1.arg2 = (int)(Math.random()*10+170);
                handler.sendMessage(msg1);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
