package com.example.mathbomb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

import org.json.JSONException;

import android.content.Context;
import android.util.Log;

public class SingleRecord {

	private static final String FILENAME = "score.json";
	private ArrayList<Record> mRecord;

	private JSONScoreSerializer mJsonSerializer;

	private static SingleRecord sModelSingleton;
	private Context mAppContext;

	private SingleRecord(Context appContext) {
		mAppContext = appContext;
		mJsonSerializer = new JSONScoreSerializer(mAppContext, FILENAME);
        try {
            mRecord = mJsonSerializer.loadDetails();
        } catch (Exception e) {
        	mRecord = new ArrayList<Record>();
            Log.e("SingleRecord", "Error loading crimes: ", e);
        }
	}

	public static SingleRecord get(Context c) throws Exception {
		if (sModelSingleton == null) {
			sModelSingleton = new SingleRecord(c.getApplicationContext());
		}

		return sModelSingleton;

	}

    public Record getSCore(String score) {
        for (Record c : mRecord) {
            if (c.getScore().equals(score))
                return c;
        }
        return null;
    }
    
    public void addDetails(Record c) {
		mRecord.add(c);
	}    

    public ArrayList<Record> getRecord() {
        return mRecord;
    }

	public ArrayList<Record> getDetails() {
		return highScores();
	}

	public boolean saveDetails() throws JSONException, IOException {
		mJsonSerializer.saveDetails(mRecord);
		return true;
	}

	// Sorting Scores in descending order
	public ArrayList<Record> highScores() {

		ArrayList<Record> tempModelClass1 = new ArrayList<Record>();
		ArrayList<Record> tempModelClass2 = new ArrayList<Record>();
		try {
			tempModelClass1 = mJsonSerializer.loadDetails();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		Collections.sort(tempModelClass1);

		for (Record mC : tempModelClass1) {
			if (tempModelClass2.size() > 9)
				break;
			tempModelClass2.add(mC);
		}
		return tempModelClass2;
	}
}