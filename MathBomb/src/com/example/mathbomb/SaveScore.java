package com.example.mathbomb;

import java.util.Date;

import android.content.Context;

public class SaveScore {
    private Context mAppContext;

    public SaveScore(Context appContext) {
        this.mAppContext = appContext;
    }

    public void saveScore(int score, Date date, String fileName)
            throws Exception {
        Record mRecord = new Record();

        mRecord.setScore(Integer.toString(score));
        mRecord.setDate(date);

        SingleRecord saveNormal = new SingleRecord(mAppContext, fileName);
        saveNormal.addDetails(mRecord, fileName);
    }
}
