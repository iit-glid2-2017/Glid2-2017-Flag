package com.iit.flagquiz;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iit.flagquiz.core.Flag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Flag> mFlagList;

    private ImageView mFlagImg;
    private TextView mFlagTitle1;
    private TextView mFlagTitle2;
    private TextView mFlagTitle3;
    private TextView mFlagTitle4;
    private TextView mScoreText;


    private ArrayList<Flag> mQuestionFlags;
    private int mQuestionIndex = 0;

    private int mScore = 0;
    private int mWrongAnswer = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        play();
    }


    private void initViews() {
        mFlagImg = (ImageView) findViewById(R.id.flag_img);
        mFlagTitle1 = (TextView) findViewById(R.id.flag_text_1);
        mFlagTitle1.setOnClickListener(this);
        mFlagTitle2 = (TextView) findViewById(R.id.flag_text_2);
        mFlagTitle2.setOnClickListener(this);
        mFlagTitle3 = (TextView) findViewById(R.id.flag_text_3);
        mFlagTitle3.setOnClickListener(this);
        mFlagTitle4 = (TextView) findViewById(R.id.flag_text_4);

        mFlagTitle4.setOnClickListener(this);
        initData();

        mScoreText = (TextView) findViewById(R.id.score_text);
    }

    private void play() {

        mQuestionFlags.clear();


        Random random = new Random();
        int randomInt = random.nextInt(mFlagList.size());
        mQuestionFlags.add(mFlagList.get(mQuestionIndex));

        for (int i = 1; i < 4; i++) {

            Flag toAdd = mFlagList.get(randomInt);

            while (mQuestionFlags.contains(toAdd)) {
                randomInt = random.nextInt(mFlagList.size());
                toAdd = mFlagList.get(randomInt);
            }
            mQuestionFlags.add(toAdd);
        }

        Collections.shuffle(mQuestionFlags);

        TypedArray imgs = getResources().obtainTypedArray(R.array.countries_drawables_array);

        mFlagImg.setImageResource(imgs.getResourceId(mFlagList.get(mQuestionIndex).getId() - 1, -1));

        mFlagTitle1.setText(mQuestionFlags.get(0).getTitle());
        mFlagTitle2.setText(mQuestionFlags.get(1).getTitle());
        mFlagTitle3.setText(mQuestionFlags.get(2).getTitle());
        mFlagTitle4.setText(mQuestionFlags.get(3).getTitle());


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
        mQuestionFlags = new ArrayList<>(4);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.flag_text_1:
                validate(0);
                break;
            case R.id.flag_text_2:
                validate(1);
                break;
            case R.id.flag_text_3:
                validate(2);
                break;
            case R.id.flag_text_4:
                validate(3);
                break;

        }
    }

    private void validate(int answer) {
        Flag correctFlag = mFlagList.get(mQuestionIndex);
        Flag answerFlag = mQuestionFlags.get(answer);


        if (correctFlag.getId() == answerFlag.getId()) {
            mScore++;
            mScoreText.setText(String.format(getResources().getString(R.string.score_label), mScore));
        } else {
            mWrongAnswer--;
        }

        if (mWrongAnswer != 0) {
            play();
        } else {
            Log.v("score", "score = " + mScore);
            mScore = 0;
            mWrongAnswer = 3;
            Toast.makeText(this, "you lose", Toast.LENGTH_LONG).show();
        }
        mQuestionIndex++;
        if (mQuestionIndex == mFlagList.size()) {
            Collections.shuffle(mFlagList);
            mQuestionIndex = 0;
        }
    }


};