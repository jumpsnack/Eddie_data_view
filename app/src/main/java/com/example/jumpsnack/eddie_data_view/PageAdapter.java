package com.example.jumpsnack.eddie_data_view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONObject;

/**
 * Created by JUMPSNACK on 2016-07-19.
 */
public class PageAdapter extends PagerAdapter {
    JsonController jsonController;
    JSONObject airData;
    LayoutInflater pageInflater;
    View viewActivityPage;

    Context contextMainActivity;

    AbstractDataActivity abstractDataActivity;
    DetailDataActivity detailDataActivity;

MessageHandler handler;

    /**/
    TextView textCo2;
    TextView textCo;
    TextView textSo2;
    TextView textNo2;
    TextView textFineDust;
    TextView textO3;

    public PageAdapter(Context c, String jsonContents) {
        super();

        this.contextMainActivity = c;

        this.jsonController = new JsonController(jsonContents);

        this.pageInflater = LayoutInflater.from(contextMainActivity);

//        this.handler = handler;

        this.handler = MessageHandler.getInstance();
//        this.abstractDataActivity = new AbstractDataActivity(contextMainActivity);
        this.abstractDataActivity = new AbstractDataActivity();
        if (!this.jsonController.getJsonDataParsing()) {
            System.exit(0);
        }



    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        abstractDataActivity.setDataToWidgets(getRandomData());
        switch (position) {
            case 0:
                viewActivityPage = pageInflater.inflate(R.layout.activity_abstract_data, null);
                handler.setWidgets(viewActivityPage);

                break;
            case 1:
                viewActivityPage = pageInflater.inflate(R.layout.activity_detail_data, null);
                break;
        }


        ((ViewPager) container).addView(viewActivityPage, 0); /* Set activity to viewPager */

        return viewActivityPage;
    }


    JSONObject getRandomData() {
        int randNum = (int) (Math.random() * 5);
        JSONObject airData = jsonController.vectorAirData.get(randNum);
//        Log.i("Random Value", Integer.toString(randNum));

        return airData;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }
}
