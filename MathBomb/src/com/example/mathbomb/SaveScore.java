package com.example.mathbomb;
import java.util.Date;

import android.content.Context;
import android.content.Intent;

public class SaveScore {
	private Context mAppContext;

	public SaveScore(Context appContext) {
		this.mAppContext = appContext;
	}
	
	public void saveScore(int score, Date date) throws Exception {
		Record mClass = new Record();
		
		mClass.setScore(Integer.toString(score));
		mClass.setDate(date);

		SingleRecord.get(mAppContext).addDetails(mClass);
		SingleRecord.get(mAppContext).saveDetails();
	}
}
