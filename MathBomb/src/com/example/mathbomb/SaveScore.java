package com.example.mathbomb;

import java.util.Date;

import android.content.Context;

public class SaveScore {
    private Context mNormalAppContext;
    public SaveScore(Context appNormalContext) {
        this.mNormalAppContext = appNormalContext;
    }

    public void saveScore(int score, Date date, String fileName) throws Exception {
        Record mNormalRecord = new Record();

        mNormalRecord.setScore(Integer.toString(score));
        mNormalRecord.setDate(date);

        SingleRecord saveNormal = new SingleRecord(mNormalAppContext, fileName);
        saveNormal.addDetails(mNormalRecord, fileName);
        }
}
