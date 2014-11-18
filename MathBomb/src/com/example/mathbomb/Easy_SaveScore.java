package com.example.mathbomb;
import java.util.Date;

import android.content.Context;
import android.util.Log;

public class Easy_SaveScore {
	private Context mEasyAppContext;

	public Easy_SaveScore(Context appEasyContext) {
		this.mEasyAppContext = appEasyContext;
	}
	
	public void saveScore(int score, Date date) throws Exception {
		Easy_Record mEasyRecord = new Easy_Record();
		
		mEasyRecord.setScore(Integer.toString(score));
		mEasyRecord.setDate(date);
		
		Easy_SingleRecord.get(mEasyAppContext).addDetails(mEasyRecord);
		Easy_SingleRecord.get(mEasyAppContext).saveDetails();
		
		Log.i("SaveScore",score+"");	
		Log.i("SaveScore",date+"");;
	}
	
	
}
