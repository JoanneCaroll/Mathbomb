package com.example.mathbomb;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class Record implements Serializable, Comparable<Record>  {

    private static final String JSON_SCORE = "score";
    private static final String JSON_DATE = "date";

    private String mNormalScore;
    private Date mNormalDate;

    public Record() {
        // used for initialization
    }

    public Record(JSONObject jsonObject) throws JSONException {
        mNormalScore = jsonObject.getString(JSON_SCORE);
        mNormalDate = new Date(jsonObject.getLong(JSON_DATE));
    }

    public JSONObject toJsonObject() throws JSONException {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put(JSON_SCORE, mNormalScore);
        jsonObject.put(JSON_DATE, mNormalDate.getTime());

        return jsonObject;
    }

    public String getScore() {
        return mNormalScore;
    }

    public void setScore(String score) {
        mNormalScore = score;
    }

    public Date getDate() {
        return mNormalDate;
    }

    public void setDate(Date date) {
        mNormalDate = date;
    }

    @Override
    public int compareTo(Record another) {
        return Integer.parseInt(another.mNormalScore) - Integer.parseInt(this.mNormalScore);
    }
}