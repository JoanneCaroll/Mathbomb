package com.example.mathbomb;

import java.util.Date;

import android.content.Context;
import android.util.Log;

public class Normal_SaveScore {
	private Context mNormalAppContext;

	public Normal_SaveScore(Context appNormalContext) {
		this.mNormalAppContext = appNormalContext;
	}
	
	public void saveScore(int score, String category, Date date) throws Exception {
		Normal_Record mNormalRecord = new Normal_Record();
		
		mNormalRecord.setScore(Integer.toString(score));
		mNormalRecord.setCategory(category.toString());
		mNormalRecord.setDate(date);
		
		Normal_SingleRecord.get(mNormalAppContext).addDetails(mNormalRecord);
		Normal_SingleRecord.get(mNormalAppContext).saveDetails();
		
		Log.i("SaveScore",score+"");	
		Log.i("SaveScore",date+"");
		Log.i("SaveSocre",category+"");
	}
	
	
}
