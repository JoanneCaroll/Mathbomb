package com.example.mathbomb;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONException;

import android.content.Context;
import android.util.Log;

public class Normal_SingleRecord {

    public static final String EASYFILENAME = "scoreEasy.json";
    public static final String NORMALFILENAME = "scoreNormal.json";
    public static final String HARDFILENAME = "scoreHard.json";
    
    private ArrayList<Normal_Record> mNormalRecord;

    private Normal_JSONSerializer mNormalJsonSerializer;

    private static Normal_SingleRecord sNormalSingleRecord;

    public Normal_SingleRecord(Context context, String fileName) {
        mNormalJsonSerializer = new Normal_JSONSerializer(context, fileName);
        Log.i("SingleRecord", fileName);
        if(mNormalRecord == null)
            mNormalRecord = highScores();
        else
            mNormalRecord = new ArrayList<Normal_Record>();
    }

    public static Normal_SingleRecord get(Context c, String fileName) throws Exception {
        if (sNormalSingleRecord == null) {
            sNormalSingleRecord = new Normal_SingleRecord(c.getApplicationContext(), fileName);
            Log.i("SingleRecord", fileName);
        }
        return sNormalSingleRecord;
    }

    public ArrayList<Normal_Record> getDetails() {
        return highScores();
    }

    public void addDetails(Normal_Record c, String fileName) throws JSONException, IOException {
        mNormalRecord.add(c);
        saveDetails();
        Log.i("addDetails()", fileName );
    }

    public void saveDetails() throws JSONException, IOException {
        mNormalJsonSerializer.saveDetails(mNormalRecord);
    }

    // Sorting Scores in descending order
    public ArrayList<Normal_Record> highScores() {

        ArrayList<Normal_Record> tempRecord1 = new ArrayList<Normal_Record>();
        ArrayList<Normal_Record> tempRecord2 = new ArrayList<Normal_Record>();
        try {
            tempRecord1 = mNormalJsonSerializer.loadDetails();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        Collections.sort(tempRecord1);

        for (Normal_Record mC : tempRecord1) {
            if (tempRecord2.size() > 9)
                break;
            tempRecord2.add(mC);
        }
        return tempRecord2;
    }
}