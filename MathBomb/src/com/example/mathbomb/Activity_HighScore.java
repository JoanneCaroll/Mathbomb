package com.example.mathbomb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_HighScore extends Activity {

    private TextView 
    highscore, 
    easy, easyscore, easydate,
    normal, normalscore, normaldate, 
    hard, hardscore, harddate;
    private String highscoreshow = "";
//    private ArrayList<Normal_Record> mEasyRecord, mNormalRecord, mHardRecord;
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

        easy = (TextView)findViewById(R.id.easy);
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

        try {
            Normal_SingleRecord easySingleRecord = new Normal_SingleRecord(this, Normal_SingleRecord.EASYFILENAME);
            easySingleRecord.getDetails();
            Normal_Record normalrecord = easySingleRecord.highScores().get(0);
            highscoreshow = normalrecord.getScore();
            easyscore.setText(highscoreshow);
            easydate.setText(dateFormat.format(normalrecord.getDate())+"");
        } catch (Exception e) {	
            e.printStackTrace();
            easyscore.setText("norecord");
            easydate.setText("norecord");
        }

        try {
            Normal_SingleRecord normalSingleRecord = new Normal_SingleRecord(this, Normal_SingleRecord.NORMALFILENAME);
            normalSingleRecord.getDetails();
            Normal_Record normalrecord = normalSingleRecord.highScores().get(0);
            highscoreshow = normalrecord.getScore();
            normalscore.setText(highscoreshow);
            normaldate.setText(dateFormat.format(normalrecord.getDate())+"");
        } catch (Exception e) {
            e.printStackTrace();
            normalscore.setText("norecord");
            normaldate.setText("norecord");
        }

        try {
            Normal_SingleRecord hardSingleRecord = new Normal_SingleRecord(this, Normal_SingleRecord.HARDFILENAME);
            hardSingleRecord.getDetails();
            Normal_Record normalrecord = hardSingleRecord.highScores().get(0);
            highscoreshow = normalrecord.getScore();
            hardscore.setText(highscoreshow);
            harddate.setText(dateFormat.format(normalrecord.getDate())+"");
        } catch (Exception e) {
            e.printStackTrace();
            hardscore.setText("norecord");
            harddate.setText("norecord");
        }
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    
}