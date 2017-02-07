package com.iit.flagquiz;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.iit.flagquiz.core.Flag;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Flag> mFlagList;

    private ImageView mFlagImg;
    private TextView mFlagTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        play();
    }


    private void initViews() {
        mFlagImg = (ImageView) findViewById(R.id.flag_img);
        mFlagTitle = (TextView) findViewById(R.id.flag_text);

    }

    private void play() {
        initData();

        Log.v("skander", mFlagList.get(0).getTitle());

        TypedArray imgs = getResources().obtainTypedArray(R.array.countries_drawables_array);

        mFlagImg.setImageResource(imgs.getResourceId(mFlagList.get(0).getId()-1, -1));
        mFlagTitle.setText(mFlagList.get(0).getTitle());


    }

    private void initData() {
        mFlagList = new ArrayList<>();

        int[] idArray = getResources().getIntArray(R.array.countries_ids_array);
        int[] imgArray = getResources().getIntArray(R.array.countries_drawables_array);
        String[] nameArray = getResources().getStringArray(R.array.countries_names_array);


        for (int i = 0; i < idArray.length; i++) {
            Flag flag = new Flag(idArray[i], imgArray[i], nameArray[i]);
            mFlagList.add(flag);
        }

        Collections.shuffle(mFlagList);
    }


}
