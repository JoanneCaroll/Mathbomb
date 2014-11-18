package com.example.mathbomb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONException;

import android.content.Context;

public class Easy_SingleRecord {

	private static final String FILENAME = "scoreEasy.json";

	private ArrayList<Easy_Record> mEasyRecord;

	private Easy_JSONSerializer mEasyJsonSerializer;

	private static Easy_SingleRecord sEasySingleRecord;

	private Easy_SingleRecord(Context context) {
		mEasyJsonSerializer = new Easy_JSONSerializer(context, FILENAME);
		try {
			mEasyRecord = highScores();
		} catch (Exception e) {
			mEasyRecord = new ArrayList<Easy_Record>();
			e.printStackTrace();
		}
	}

	public static Easy_SingleRecord get(Context c) throws Exception {
		if (sEasySingleRecord == null) {
			sEasySingleRecord = new Easy_SingleRecord(c.getApplicationContext());
		}
		return sEasySingleRecord;
	}

	public ArrayList<Easy_Record> getDetails() {
		return highScores();
	}

	public void addDetails(Easy_Record c) throws JSONException, IOException {
		mEasyRecord.add(c);
		saveDetails();
	}

	public boolean saveDetails() throws JSONException, IOException {
		mEasyJsonSerializer.saveDetails(mEasyRecord);
		return true;
	}

	// Sorting Scores in descending order
	public ArrayList<Easy_Record> highScores() {

		ArrayList<Easy_Record> tempRecord1 = new ArrayList<Easy_Record>();
		ArrayList<Easy_Record> tempRecord2 = new ArrayList<Easy_Record>();
		try {
			tempRecord1 = mEasyJsonSerializer.loadDetails();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Collections.sort(tempRecord1);

		for (Easy_Record mC : tempRecord1) {
			if (tempRecord2.size() > 9)
				break;
			tempRecord2.add(mC);
		}
		return tempRecord2;
	}
}