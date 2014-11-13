package com.example.mathbomb;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class Record implements Serializable, Comparable<Record>  {
	
	private static final String JSON_SCORE = "score";
	private static final String JSON_DATE = "date";
	private static final String JSON_CATEGORY = "category";
	
	private String mScore, mCategory;
	private Date mDate;

	public Record() {
		// used for initialization
	}

	public Record(JSONObject jsonObject) throws JSONException {
		mScore = jsonObject.getString(JSON_SCORE);
		mCategory = jsonObject.getString(JSON_CATEGORY);
        mDate = new Date(jsonObject.getLong(JSON_DATE));
	}

	public JSONObject toJsonObject() throws JSONException {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put(JSON_SCORE, mScore);
		jsonObject.put(JSON_CATEGORY, mCategory);
        jsonObject.put(JSON_DATE, mDate.getTime());

		return jsonObject;
	}

	public String getScore() {
		return mScore;
	}

	public void setScore(String score) {
		mScore = score;
	}
	
	public String getCategory() {
		return mCategory;
	}

	public void setCategory(String category) {
		mCategory = category;
	}

	public Date getDate() {
		return mDate;
	}

	public void setDate(Date date) {
		mDate = date;
	}

	@Override
	public int compareTo(Record another) {
		return Integer.parseInt(another.mScore) - Integer.parseInt(this.mScore);
	}
}