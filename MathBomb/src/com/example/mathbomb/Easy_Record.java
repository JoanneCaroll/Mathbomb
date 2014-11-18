package com.example.mathbomb;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class Easy_Record implements Serializable, Comparable<Easy_Record>  {

	private static final String JSON_EasySCORE = "score";
	private static final String JSON_EasyDATE = "date";

	private String mEasyScore;
	private Date mEasyDate;

	public Easy_Record() {
		// used for initialization
	}

	public Easy_Record(JSONObject jsonObject) throws JSONException {
		mEasyScore = jsonObject.getString(JSON_EasySCORE);
		mEasyDate = new Date(jsonObject.getLong(JSON_EasyDATE));
	}

	public JSONObject toJsonObject() throws JSONException {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put(JSON_EasySCORE, mEasyScore);
		jsonObject.put(JSON_EasyDATE, mEasyDate.getTime());

		return jsonObject;
	}

	public String getScore() {
		return mEasyScore;
	}

	public void setScore(String score) {
		mEasyScore = score;
	}

	public Date getDate() {
		return mEasyDate;
	}

	public void setDate(Date date) {
		mEasyDate = date;
	}

	@Override
	public int compareTo(Easy_Record another) {
		return Integer.parseInt(another.mEasyScore) - Integer.parseInt(this.mEasyScore);
	}
}