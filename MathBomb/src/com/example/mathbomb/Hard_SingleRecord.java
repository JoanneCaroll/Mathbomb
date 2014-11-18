package com.example.mathbomb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONException;

import android.content.Context;

public class Hard_SingleRecord {

    private static final String FILENAME = "scoreHard.json";

    private ArrayList<Hard_Record> mHardRecord;

    private Hard_JSONSerializer mHardJsonSerializer;

    private static Hard_SingleRecord sHardSingleRecord;

    private Hard_SingleRecord(Context context) {
        mHardJsonSerializer = new Hard_JSONSerializer(context, FILENAME);
        try {
            mHardRecord = highScores();
        } catch (Exception e) {
            mHardRecord = new ArrayList<Hard_Record>();
            e.printStackTrace();
        }
    }

    public static Hard_SingleRecord get(Context c) throws Exception {
        if (sHardSingleRecord == null) {
            sHardSingleRecord = new Hard_SingleRecord(c.getApplicationContext());
        }
        return sHardSingleRecord;
    }

    public ArrayList<Hard_Record> getDetails() {
        return highScores();
    }

    public void addDetails(Hard_Record c) throws JSONException, IOException {
        mHardRecord.add(c);
        saveDetails();
    }

    public void saveDetails() throws JSONException, IOException {
        mHardJsonSerializer.saveDetails(mHardRecord);
    }

    // Sorting Scores in descending order
    public ArrayList<Hard_Record> highScores() {

        ArrayList<Hard_Record> tempRecord1 = new ArrayList<Hard_Record>();
        ArrayList<Hard_Record> tempRecord2 = new ArrayList<Hard_Record>();
        try {
            tempRecord1 = mHardJsonSerializer.loadDetails();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        Collections.sort(tempRecord1);

        for (Hard_Record mC : tempRecord1) {
            if (tempRecord2.size() > 9)
                break;
            tempRecord2.add(mC);
        }
        return tempRecord2;
    }
}