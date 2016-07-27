package com.example.jumpsnack.eddie_data_view;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by JUMPSNACK on 7/25/2016.
 */
public class MessageHandler extends Handler {

    private static MessageHandler instance = new MessageHandler();

    private static TextView textHeartRate;
    private static TextView textCo2;
    private static TextView textCo;
    private static TextView textSo2;
    private static TextView textNo2;
    private static TextView textFineDust;
    private static TextView textO3;

    public static final int MSG_DATA_AIR_POLUTION = 0;
    public static final int MSG_DATA_HEART_RATE = 1;

    // AbstractDataActivity abstractDataActivity = null;

    //public MessageHandler(){
//        this.abstractDataActivity = new AbstractDataActivity();
//    }

    private MessageHandler() {

    }

    public static MessageHandler getInstance() {
        return instance;
    }

    public void setWidgets(View viewActivityPage) {
        textHeartRate = (TextView) viewActivityPage.findViewById(R.id.text_abstract_heart_rate);
        textCo2 = (TextView) viewActivityPage.findViewById(R.id.text_abstract_co2);
        textCo = (TextView) viewActivityPage.findViewById(R.id.text_abstract_co);
        textSo2 = (TextView) viewActivityPage.findViewById(R.id.text_abstract_so2);
        textNo2 = (TextView) viewActivityPage.findViewById(R.id.text_abstract_no2);
        textFineDust = (TextView) viewActivityPage.findViewById(R.id.text_abstract_fine_dust);
        textO3 = (TextView) viewActivityPage.findViewById(R.id.text_abstract_o3);
    }


    public void setDataToWidgets(JSONObject airData) {
        try {

            textCo2.setText(airData.getString("co2"));
            textCo.setText(airData.getString("co"));
            textSo2.setText(airData.getString("so2"));
            textNo2.setText(airData.getString("no2"));
            textFineDust.setText(airData.getString("pm2.5"));
            textO3.setText(airData.getString("o3"));

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        switch (msg.arg1) {
            case MSG_DATA_AIR_POLUTION:
                JSONObject getData = (JSONObject) msg.obj;
                if (textCo2 != null)
                    setDataToWidgets(getData);
                break;
            case MSG_DATA_HEART_RATE:
                if (textHeartRate != null)
                    textHeartRate.setText(Integer.toString(msg.arg2));
                break;
        }
        //abstractDataActivity.setDataToWidgets(getData);
    }
}
