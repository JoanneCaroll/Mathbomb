package com.example.mathbomb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.content.Context;

public class Easy_JSONSerializer {
	private Context mEasyContext;
	private String mEasyFilename;

	public Easy_JSONSerializer(Context cEasy, String fnameEasy) {
		mEasyContext = cEasy;
		mEasyFilename = fnameEasy;
	}
   
	public void saveDetails(ArrayList<Easy_Record> mRecord)
			throws JSONException, IOException {
		JSONArray jsonArray = new JSONArray();

		for (Easy_Record er : mRecord) {
			jsonArray.put(er.toJsonObject());
		}

		Writer writer = null;

		OutputStream outputStream = mEasyContext.openFileOutput(mEasyFilename, Context.MODE_PRIVATE);
		writer = new OutputStreamWriter(outputStream);
		writer.write(jsonArray.toString());

		if (writer != null) {
			writer.close();
		}
	}

	public ArrayList<Easy_Record> loadDetails() throws Exception {

		ArrayList<Easy_Record> mRecord = new ArrayList<Easy_Record>();
		BufferedReader reader = null;

		InputStream inputStream = mEasyContext.openFileInput(mEasyFilename);
		reader = new BufferedReader(new InputStreamReader(inputStream));

		StringBuilder json_string = new StringBuilder();
		String line = null;

		while ((line = reader.readLine()) != null) {
			json_string.append(line);
		}

		JSONArray jsonArray = (JSONArray) new JSONTokener(
				json_string.toString()).nextValue();

		for (int i = 0; i < jsonArray.length(); i++) {
			mRecord.add(new Easy_Record(jsonArray.getJSONObject(i)));
		}

		if (reader != null)
			reader.close();

		return mRecord;
	}
}