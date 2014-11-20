package com.example.mathbomb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONException;

import android.content.Context;

public class SingleRecord {

    public static final String EASYFILENAME = "scoreEasy.json";
    public static final String NORMALFILENAME = "scoreNormal.json";
    public static final String HARDFILENAME = "scoreHard.json";

    private ArrayList<Record> mNormalRecord;

    private JSONSerializer mNormalJsonSerializer;

    private static SingleRecord sNormalSingleRecord;

    public SingleRecord(Context context, String fileName) {
        mNormalJsonSerializer = new JSONSerializer(context, fileName);
        if (mNormalRecord == null)
            mNormalRecord = highScores();
        else
            mNormalRecord = new ArrayList<Record>();
    }

    public static SingleRecord get(Context c, String fileName) throws Exception {
        if (sNormalSingleRecord == null) {
            sNormalSingleRecord = new SingleRecord(c.getApplicationContext(),
                    fileName);
        }
        return sNormalSingleRecord;
    }

    public ArrayList<Record> getDetails() {
        return highScores();
    }

    public void addDetails(Record c, String fileName) throws JSONException,
            IOException {
        mNormalRecord.add(c);
        saveDetails();
    }

    public void saveDetails() throws JSONException, IOException {
        mNormalJsonSerializer.saveDetails(mNormalRecord);
    }

    // Sorting Scores in descending order
    public ArrayList<Record> highScores() {

        ArrayList<Record> tempRecord1 = new ArrayList<Record>();
        ArrayList<Record> tempRecord2 = new ArrayList<Record>();
        try {
            tempRecord1 = mNormalJsonSerializer.loadDetails();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        Collections.sort(tempRecord1);
        for (Record mC : tempRecord1) {
            if (tempRecord2.size() > 9)
                break;
            tempRecord2.add(mC);
        }
        return tempRecord2;
    }
}