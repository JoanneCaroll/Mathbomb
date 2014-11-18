package com.example.mathbomb;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONException;

import android.content.Context;

public class Normal_SingleRecord {

	private static final String FILENAME = "scoreNormal.json";

	private ArrayList<Normal_Record> mNormalRecord;

	private Normal_JSONSerializer mNormalJsonSerializer;

	private static Normal_SingleRecord sNormalSingleRecord;

	private Normal_SingleRecord(Context context) {
		mNormalJsonSerializer = new Normal_JSONSerializer(context, FILENAME);
		try {
			mNormalRecord = highScores();
		} catch (Exception e) {
			mNormalRecord = new ArrayList<Normal_Record>();
			e.printStackTrace();
		}
	}

	public static Normal_SingleRecord get(Context c) throws Exception {
		if (sNormalSingleRecord == null) {
			sNormalSingleRecord = new Normal_SingleRecord(c.getApplicationContext());
		}
		return sNormalSingleRecord;
	}

	public ArrayList<Normal_Record> getDetails() {
		return highScores();
	}

	public void addDetails(Normal_Record c) throws JSONException, IOException {
		mNormalRecord.add(c);
		saveDetails();
	}

	public boolean saveDetails() throws JSONException, IOException {
		mNormalJsonSerializer.saveDetails(mNormalRecord);
		return true;
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