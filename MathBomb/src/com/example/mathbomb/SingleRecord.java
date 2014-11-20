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
    private static final int maxArraySize = 9;
    private ArrayList<Record> mRecord;

    private JSONSerializer mJsonSerializer;

    private static SingleRecord sSingleRecord;

    public SingleRecord(Context context, String fileName) {
        mJsonSerializer = new JSONSerializer(context, fileName);
        if (mRecord == null) {
            mRecord = highScores();
        } else {
            mRecord = new ArrayList<Record>();
        }
    }

    public static SingleRecord get(Context c, String fileName) throws Exception {
        if (sSingleRecord == null) {
            sSingleRecord = new SingleRecord(c.getApplicationContext(),
                    fileName);
        }
        return sSingleRecord;
    }

    public ArrayList<Record> getDetails() {
        return highScores();
    }

    public void addDetails(Record c, String fileName) throws JSONException,
            IOException {
        mRecord.add(c);
        saveDetails();
    }

    public void saveDetails() throws JSONException, IOException {
        mJsonSerializer.saveDetails(mRecord);
    }

    // Sorting Scores in descending order
    public ArrayList<Record> highScores() {

        ArrayList<Record> tempRecord1 = new ArrayList<Record>();
        ArrayList<Record> tempRecord2 = new ArrayList<Record>();
        try {
            tempRecord1 = mJsonSerializer.loadDetails();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        Collections.sort(tempRecord1);
        for (Record mC : tempRecord1) {
            if (tempRecord2.size() > maxArraySize) {
                break;
            }
            tempRecord2.add(mC);
        }
        return tempRecord2;
    }
}