package com.example.mathbomb;
import java.util.Date;

import android.content.Context;

public class Hard_SaveScore {
	private Context mHardAppContext;

	public Hard_SaveScore(Context appHardContext) {
		this.mHardAppContext = appHardContext;
	}
	
	public void saveScore(int score, String category, Date date) throws Exception {
		Hard_Record mHardRecord = new Hard_Record();
		
		mHardRecord.setScore(Integer.toString(score));
		mHardRecord.setCategory(category.toString());
		mHardRecord.setDate(date);
		
		Hard_SingleRecord.get(mHardAppContext).addDetails(mHardRecord);
		Hard_SingleRecord.get(mHardAppContext).saveDetails();
	}
	
	
}
