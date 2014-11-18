package com.example.mathbomb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_HighScore extends Activity {

    private TextView 
    highscore, 
    easy, easyscore, easydate,
    normal, normalscore, normaldate, 
    hard, hardscore, harddate;
    private Button okbutton;
    @SuppressLint({ "SimpleDateFormat", "CutPasteId" })
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        highscore = (TextView)findViewById(R.id.highscoreshow);
        highscore.setText(R.string.highscoretitle); 

        easy = (TextView)findViewById(R.id.easyscore);
        easy.setText(Activity_StartGame.category[0]);
        easyscore = (TextView)findViewById(R.id.easyscore);
        easydate = (TextView)findViewById(R.id.easydate);

        normal = (TextView)findViewById(R.id.normal);
        normal.setText(Activity_StartGame.category[1]);
        normalscore = (TextView)findViewById(R.id.normalscore);
        normaldate = (TextView)findViewById(R.id.normaldate); 

        hard = (TextView)findViewById(R.id.hard);
        hard.setText(Activity_StartGame.category[2]);
        hardscore = (TextView)findViewById(R.id.hardscore);
        harddate = (TextView)findViewById(R.id.harddate);

        okbutton = (Button)findViewById(R.id.ok_highscore);
        okbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        ArrayList<Easy_Record> mEasyRecord = null;
        ArrayList<Normal_Record> mNormalRecord = null;
        ArrayList<Hard_Record> mHardRecord = null;

        try {
            mEasyRecord = Easy_SingleRecord.get(this).getDetails();
            Easy_Record recordeasy = mEasyRecord.get(0);
            easyscore.setText(recordeasy.getScore());
            easydate.setText(dateFormat.format(recordeasy.getDate())+"");
        } catch (Exception e) {	
            e.printStackTrace();
            easyscore.setText("norecord");
            easydate.setText("norecord");
        }

        try {
            mNormalRecord = Normal_SingleRecord.get(this).getDetails();
            Normal_Record recordNormal = mNormalRecord.get(0);
            normalscore.setText(recordNormal.getScore());
            normaldate.setText(dateFormat.format(recordNormal.getDate())+"");
        } catch (Exception e) {
            e.printStackTrace();
            normalscore.setText("norecord");
            normaldate.setText("norecord");
        }

        try {
            mHardRecord = Hard_SingleRecord.get(this).getDetails();
            Hard_Record recordHard = mHardRecord.get(0);
            hardscore.setText(recordHard.getScore());
            harddate.setText(dateFormat.format(recordHard.getDate())+"");
        } catch (Exception e) {
            e.printStackTrace();
            hardscore.setText("norecord");
            harddate.setText("norecord");
        }
    }
}