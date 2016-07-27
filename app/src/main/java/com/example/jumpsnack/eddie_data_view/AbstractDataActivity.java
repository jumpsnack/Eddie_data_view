package com.example.jumpsnack.eddie_data_view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by JUMPSNACK on 2016-07-19.
 */
public class AbstractDataActivity extends AppCompatActivity {
    //View viewAbstractDataActivity;

    TextView textCo2;
    TextView textCo;
    TextView textSo2;
    TextView textNo2;
    TextView textFineDust;
    TextView textO3;
    Intent intent;
    String jsonString;

    Context contextMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abstract_data);
        Log.w("onCreate Call!", "Custom Form is Called");
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        textCo2 = (TextView) findViewById(R.id.text_abstract_co2);
//        textCo = (TextView) findViewById(R.id.text_abstract_co);
//        textSo2 = (TextView) findViewById(R.id.text_abstract_so2);
//        textNo2 = (TextView) findViewById(R.id.text_abstract_no2);
//        textFineDust = (TextView) findViewById(R.id.text_abstract_fine_dust);
//        textO3 = (TextView) findViewById(R.id.text_abstract_o3);
//    }

//    public AbstractDataActivity() {
//        textCo2 = (TextView) findViewById(R.id.text_abstract_co2);
//        textCo = (TextView) findViewById(R.id.text_abstract_co);
//        textSo2 = (TextView) findViewById(R.id.text_abstract_so2);
//        textNo2 = (TextView) findViewById(R.id.text_abstract_no2);
//        textFineDust = (TextView) findViewById(R.id.text_abstract_fine_dust);
//        textO3 = (TextView) findViewById(R.id.text_abstract_o3);
//    }

//    public AbstractDataActivity(Context contextMainActivity) {
//        this.contextMainActivity = contextMainActivity;
////        initWidgets();
//    }

//    private void initWidgets() {
//        MainActivity activityMain = (MainActivity) contextMainActivity;
//
//        textCo2 = (TextView) activityMain.findViewById(R.id.text_abstract_co2);
//        textCo = (TextView) activityMain.findViewById(R.id.text_abstract_co);
//        textSo2 = (TextView) activityMain.findViewById(R.id.text_abstract_so2);
//        textNo2 = (TextView) activityMain.findViewById(R.id.text_abstract_no2);
//        textFineDust = (TextView) activityMain.findViewById(R.id.text_abstract_fine_dust);
//        textO3 = (TextView) activityMain.findViewById(R.id.text_abstract_o3);
//    }

    public void setDataToWidgets(JSONObject airData) {
        try {

            textCo2.setText(airData.getString("co2"));
            Log.d("Co2 Data", airData.getString("co2"));
            textCo2.invalidate();
            textCo.setText(airData.getString("co"));
            textCo.invalidate();
            textSo2.setText(airData.getString("so2"));
            textSo2.invalidate();
            textNo2.setText(airData.getString("no2"));
            textNo2.invalidate();
            textFineDust.setText(airData.getString("pm2.5"));
            textFineDust.invalidate();
            textO3.setText(airData.getString("o3"));
            textO3.invalidate();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
