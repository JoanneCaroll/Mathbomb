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
    txthighscore, 
    txteasy, txteasyscore, txteasydate,
    txtnormal, txtnormalscore, txtnormaldate, 
    txthard, txthardscore, txtharddate;
    private String highscoreshow = "";
    private Button okbutton;
    @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        
        txthighscore = (TextView)findViewById(R.id.highscoreshow);
        txthighscore.setText(R.string.highscoretitle); 

        txteasy = (TextView)findViewById(R.id.easy);
        txteasy.setText(Activity_StartGame.category[0]);
        txteasyscore = (TextView)findViewById(R.id.easyscore);
        txteasydate = (TextView)findViewById(R.id.easydate);

        txtnormal = (TextView)findViewById(R.id.normal);
        txtnormal.setText(Activity_StartGame.category[1]);
        txtnormalscore = (TextView)findViewById(R.id.normalscore);
        txtnormaldate = (TextView)findViewById(R.id.normaldate); 

        txthard = (TextView)findViewById(R.id.hard);
        txthard.setText(Activity_StartGame.category[2]);
        txthardscore = (TextView)findViewById(R.id.hardscore);
        txtharddate = (TextView)findViewById(R.id.harddate);

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
                txteasyscore.setText(highscoreshow);
                txteasydate.setText(dateFormat.format(normalrecord.getDate())+"");
            } else if(fileName == SingleRecord.NORMALFILENAME)
            {
                txtnormalscore.setText(highscoreshow);
                txtnormaldate.setText(dateFormat.format(normalrecord.getDate())+"");
            } else if(fileName == SingleRecord.HARDFILENAME)
            {
                txthardscore.setText(highscoreshow);
                txtharddate.setText(dateFormat.format(normalrecord.getDate())+"");
            }
        } catch (Exception e) { 
            e.printStackTrace();
            if(fileName == SingleRecord.EASYFILENAME)
            {
                txteasyscore.setText("norecord");
                txteasydate.setText("norecord");
            } else if(fileName == SingleRecord.NORMALFILENAME)
            {
                txtnormalscore.setText("norecord");
                txtnormaldate.setText("norecord");
            } else if(fileName == SingleRecord.HARDFILENAME)
            {
                txthardscore.setText("norecord");
                txtharddate.setText("norecord");
            }
        }
    }
}