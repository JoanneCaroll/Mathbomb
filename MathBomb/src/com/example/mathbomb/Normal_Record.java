package com.example.mathbomb;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class Normal_Record implements Serializable, Comparable<Normal_Record>  {
	
	private static final String JSON_NormalSCORE = "score";
	private static final String JSON_NormalDATE = "date";
	private static final String JSON_NormalCATEGORY = "category";
	
	private String mNormalScore, mNormalCategory;
	private Date mNormalDate;

	public Normal_Record() {
		// used for initialization
	}

	public Normal_Record(JSONObject jsonObject) throws JSONException {
		mNormalScore = jsonObject.getString(JSON_NormalSCORE);
		mNormalCategory = jsonObject.getString(JSON_NormalCATEGORY);
		mNormalDate = new Date(jsonObject.getLong(JSON_NormalDATE));
	}

	public JSONObject toJsonObject() throws JSONException {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put(JSON_NormalSCORE, mNormalScore);
		jsonObject.put(JSON_NormalCATEGORY, mNormalCategory);
        jsonObject.put(JSON_NormalDATE, mNormalDate.getTime());

		return jsonObject;
	}

	public String getScore() {
		return mNormalScore;
	}

	public void setScore(String score) {
		mNormalScore = score;
	}
	
	public String getCategory() {
		return mNormalCategory;
	}

	public void setCategory(String category) {
		mNormalCategory = category;
	}

	public Date getDate() {
		return mNormalDate;
	}

	public void setDate(Date date) {
		mNormalDate = date;
	}

	@Override
	public int compareTo(Normal_Record another) {
		return Integer.parseInt(another.mNormalScore) - Integer.parseInt(this.mNormalScore);
	}
}