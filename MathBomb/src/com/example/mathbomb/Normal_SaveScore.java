package com.example.mathbomb;

import java.util.Date;

import android.content.Context;

public class Normal_SaveScore {
    private Context mNormalAppContext;
    public Normal_SaveScore(Context appNormalContext) {
        this.mNormalAppContext = appNormalContext;
    }

    public void saveScore(int score, Date date, String fileName) throws Exception {
        Normal_Record mNormalRecord = new Normal_Record();

        mNormalRecord.setScore(Integer.toString(score));
        mNormalRecord.setDate(date);

        Normal_SingleRecord saveNormal = new Normal_SingleRecord(mNormalAppContext, fileName);
        saveNormal.addDetails(mNormalRecord, fileName);
        }
}
