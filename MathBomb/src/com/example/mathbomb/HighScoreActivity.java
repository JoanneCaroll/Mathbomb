package com.example.mathbomb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HighScoreActivity extends Activity {

	private TextView 
		highscore, 
		easy, easyscore, easydate,
		normal, normalscore, normaldate, 
		hard, hardscore, harddate;
	private Button okbutton;
	
	@SuppressLint("SimpleDateFormat")
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_highscore);
		
		highscore = (TextView)findViewById(R.id.highscoreshow);
		easy = (TextView)findViewById(R.id.easy);
    	easy.setText(StartGameActivity.category[0]);
    	normal = (TextView)findViewById(R.id.normal);
		normal.setText(StartGameActivity.category[1]);
		hard = (TextView)findViewById(R.id.hard);
		hard.setText(StartGameActivity.category[2]);
		okbutton = (Button)findViewById(R.id.ok_highscore);
		okbutton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
		ArrayList<Record> mRecord = null;
        try {
			mRecord = SingleRecord.get(this).getDetails();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			
			for( int i=0; i<mRecord.size(); i++)
			{
				Record record = mRecord.get(i);
				easyscore = (TextView)findViewById(R.id.easyscore);
				easyscore.setText(Integer.toString(MainMenuActivity.prevEasyScore));    		 
		    	easydate = (TextView)findViewById(R.id.easydate); 
		    	easydate.setText(dateFormat.format(MainMenuActivity.prevEasyDate)+"");
		    	
		    	normalscore = (TextView)findViewById(R.id.normalscore);
		    	normalscore.setText(Integer.toString(MainMenuActivity.prevNormalScore));     		 
		     	normaldate = (TextView)findViewById(R.id.normaldate);
		     	normaldate.setText(dateFormat.format(MainMenuActivity.prevNormalDate)+"");
		     	
		    	hardscore = (TextView)findViewById(R.id.hardscore);
		    	hardscore.setText(Integer.toString(MainMenuActivity.prevHardScore));     		 
		     	harddate = (TextView)findViewById(R.id.harddate);
		     	harddate.setText(dateFormat.format(MainMenuActivity.prevHardDate)+"");
		     	
			}				
     	} catch (Exception e) {
			
     		e.printStackTrace(); 	
     		
		   	easyscore = (TextView)findViewById(R.id.easyscore);
		   	easyscore.setText("norecord");
		   	easydate = (TextView)findViewById(R.id.easydate);
	   		easydate.setText("norecord");	
	   		
	    	normalscore = (TextView)findViewById(R.id.normalscore);
	    	normalscore.setText("norecord");	     		 
	     	normaldate = (TextView)findViewById(R.id.normaldate);
	     	normaldate.setText("norecord");	     	
	     	
	    	hardscore = (TextView)findViewById(R.id.hardscore);
	    	hardscore.setText("norecord");	     		 
	     	harddate = (TextView)findViewById(R.id.harddate);
	     	harddate.setText("norecord"); 	    	
		}
	}
	
}