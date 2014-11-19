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
    @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        

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

        showScoreDate(SingleRecord.EASYFILENAME);
        showScoreDate(SingleRecord.NORMALFILENAME);
        showScoreDate(SingleRecord.HARDFILENAME);
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
    
    private void showScoreDate(String fileName) {
        try {
            SingleRecord singleRecord = new SingleRecord(this, fileName);
            singleRecord.getDetails();
            Record normalrecord = singleRecord.highScores().get(0);
            highscoreshow = normalrecord.getScore();
            if(fileName == SingleRecord.EASYFILENAME)
            {
                easyscore.setText(highscoreshow);
                easydate.setText(dateFormat.format(normalrecord.getDate())+"");
            } else if(fileName == SingleRecord.NORMALFILENAME)
            {
                normalscore.setText(highscoreshow);
                normaldate.setText(dateFormat.format(normalrecord.getDate())+"");
            } else if(fileName == SingleRecord.HARDFILENAME)
            {
                hardscore.setText(highscoreshow);
                harddate.setText(dateFormat.format(normalrecord.getDate())+"");
            }
        } catch (Exception e) { 
            e.printStackTrace();
            if(fileName == SingleRecord.EASYFILENAME)
            {
                easyscore.setText("norecord");
                easydate.setText("norecord");
            } else if(fileName == SingleRecord.NORMALFILENAME)
            {
                normalscore.setText("norecord");
                normaldate.setText("norecord");
            } else if(fileName == SingleRecord.HARDFILENAME)
            {
                hardscore.setText("norecord");
                harddate.setText("norecord");
            }
        }
    }
}