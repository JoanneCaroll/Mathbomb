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

public class HighScoreActivity extends Activity {

	TextView score, date, highscore;
	Button okbutton;
	@SuppressLint("SimpleDateFormat")
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_highscore);
		ArrayList<Record> mRecord = null;
        try {
			mRecord = SingleRecord.get(this).getDetails();
			Record record = mRecord.get(0);
        	highscore = (TextView)findViewById(R.id.highscoreshow);
    		
            score = (TextView)findViewById(R.id.score);
    		score.setText(record.getScore().toString());
    		 
    		date = (TextView)findViewById(R.id.date);
    		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    		date.setText(dateFormat.format(record.getDate()) + "");
    		
    		okbutton = (Button)findViewById(R.id.ok_highscore);
    		okbutton.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    				finish();
    			}
    		});
			
		} catch (Exception e) {
			e.printStackTrace(); 
			highscore = (TextView)findViewById(R.id.highscoreshow);
			
	   	     score = (TextView)findViewById(R.id.score);
	   		 score.setText("norecord");
	   		 date = (TextView)findViewById(R.id.date);
	   			date.setText("norecord");
	   			
	   			okbutton = (Button)findViewById(R.id.ok_highscore);
	   			okbutton.setOnClickListener(new View.OnClickListener() {
	   				public void onClick(View v) {
	   					finish();
	   				}
   			});
		}
	}
	
}