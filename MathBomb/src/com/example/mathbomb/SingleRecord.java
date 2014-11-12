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

	private static SingleRecord sSingleRecord;

	private SingleRecord(Context context) {
		mJsonSerializer = new JSONScoreSerializer(context, FILENAME);
		try {
			mRecord = highScores();
		} catch (Exception e) {
			mRecord = new ArrayList<Record>();
			e.printStackTrace();
		}
	}

	public static SingleRecord get(Context c) throws Exception {
		if (sSingleRecord == null) {
			sSingleRecord = new SingleRecord(c.getApplicationContext());
		}

		return sSingleRecord;

	}

	public ArrayList<Record> getDetails() {
		return highScores();
	}

	public void addDetails(Record c) throws JSONException, IOException {
		mRecord.add(c);
		saveDetails();
	}

	public boolean saveDetails() throws JSONException, IOException {
		mJsonSerializer.saveDetails(mRecord);
		return true;
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
			if (tempRecord2.size() > 9)
				break;
			tempRecord2.add(mC);
		}
		return tempRecord2;
	}
	
}