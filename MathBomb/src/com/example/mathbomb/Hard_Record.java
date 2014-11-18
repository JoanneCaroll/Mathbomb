package com.example.mathbomb;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class Hard_Record implements Serializable, Comparable<Hard_Record>  {

	private static final String JSON_HardSCORE = "score";
	private static final String JSON_HardDATE = "date";

	private String mHardScore;
	private Date mHardDate;

	public Hard_Record() {
		// used for initialization
	}

	public Hard_Record(JSONObject jsonObject) throws JSONException {
		mHardScore = jsonObject.getString(JSON_HardSCORE);
		mHardDate = new Date(jsonObject.getLong(JSON_HardDATE));
	}

	public JSONObject toJsonObject() throws JSONException {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put(JSON_HardSCORE, mHardScore);
		jsonObject.put(JSON_HardDATE, mHardDate.getTime());

		return jsonObject;
	}

	public String getScore() {
		return mHardScore;
	}

	public void setScore(String score) {
		mHardScore = score;
	}

	public Date getDate() {
		return mHardDate;
	}

	public void setDate(Date date) {
		mHardDate = date;
	}

	@Override
	public int compareTo(Hard_Record another) {
		return Integer.parseInt(another.mHardScore) - Integer.parseInt(this.mHardScore);
	}
}