package com.example.mathbomb;
import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SaveScore {
	private Context mAppContext;

	public SaveScore(Context appContext) {
		this.mAppContext = appContext;
	}
	
	public void saveScore(int score, Date date) throws Exception {
		Record mRecord = new Record();
		
		mRecord.setScore(Integer.toString(score));
		mRecord.setDate(date);
		SingleRecord.get(mAppContext).addDetails(mRecord);
		SingleRecord.get(mAppContext).saveDetails();
		Log.i("SaveScore",score+"");	
		Log.i("SaveScore",date+"");
	}
	
	
}
