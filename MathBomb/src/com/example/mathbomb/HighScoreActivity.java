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

public class HighScoreActivity extends Activity {

    private TextView txtHighScore, txtEasy, txtEasyScore, txtEasyDate,
            txtNormal, txtNormalScore, txtNormalDate, txtHard, txtHardScore,
            txtHardDate;
    private String highScoreShow = "", noRecord="";
    private Button okButton;
    @SuppressLint("SimpleDateFormat")
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        txtHighScore = (TextView) findViewById(R.id.highscoreshow);
        txtHighScore.setText(R.string.highscoretitle);

        txtEasy = (TextView) findViewById(R.id.easy);
        txtEasy.setText(StartGameActivity.category[0]);
        txtEasyScore = (TextView) findViewById(R.id.easyscore);
        txtEasyDate = (TextView) findViewById(R.id.easydate);

        txtNormal = (TextView) findViewById(R.id.normal);
        txtNormal.setText(StartGameActivity.category[1]);
        txtNormalScore = (TextView) findViewById(R.id.normalscore);
        txtNormalDate = (TextView) findViewById(R.id.normaldate);

        txtHard = (TextView) findViewById(R.id.hard);
        txtHard.setText(StartGameActivity.category[2]);
        txtHardScore = (TextView) findViewById(R.id.hardscore);
        txtHardDate = (TextView) findViewById(R.id.harddate);

        okButton = (Button) findViewById(R.id.ok_highscore);
        okButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        showScoreDate(SingleRecord.EASYFILENAME);
        showScoreDate(SingleRecord.NORMALFILENAME);
        showScoreDate(SingleRecord.HARDFILENAME);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showScoreDate(String fileName) {
        try {
            SingleRecord singleRecord = new SingleRecord(this, fileName);
            singleRecord.getDetails();
            Record normalrecord = singleRecord.highScores().get(0);
            highScoreShow = normalrecord.getScore();
            if (fileName == SingleRecord.EASYFILENAME) {
                txtEasyScore.setText(highScoreShow);
                txtEasyDate.setText(dateFormat.format(normalrecord.getDate())
                        + "");
            } else if (fileName == SingleRecord.NORMALFILENAME) {
                txtNormalScore.setText(highScoreShow);
                txtNormalDate.setText(dateFormat.format(normalrecord.getDate())
                        + "");
            } else if (fileName == SingleRecord.HARDFILENAME) {
                txtHardScore.setText(highScoreShow);
                txtHardDate.setText(dateFormat.format(normalrecord.getDate())
                        + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (fileName == SingleRecord.EASYFILENAME) {
                txtEasyScore.setText(noRecord);
                txtEasyDate.setText(noRecord);
            } else if (fileName == SingleRecord.NORMALFILENAME) {
                txtNormalScore.setText(noRecord);
                txtNormalDate.setText(noRecord);
            } else if (fileName == SingleRecord.HARDFILENAME) {
                txtHardScore.setText(noRecord);
                txtHardDate.setText(noRecord);
            }
        }
    }
}