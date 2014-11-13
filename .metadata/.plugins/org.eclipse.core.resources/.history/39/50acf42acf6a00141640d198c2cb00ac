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

public class JSONScoreSerializer {
	private Context mContext;
	private String mFilename;

	public JSONScoreSerializer(Context c, String fname) {
		mContext = c;
		mFilename = fname;
	}

	public void saveDetails(ArrayList<Record> modelClasses)
			throws JSONException, IOException {
		JSONArray jsonArray = new JSONArray();

		for (Record mc : modelClasses) {
			jsonArray.put(mc.toJsonObject());
		}

		Writer writer = null;

		OutputStream outputStream = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
		writer = new OutputStreamWriter(outputStream);
		writer.write(jsonArray.toString());

		if (writer != null) {
			writer.close();
		}
	}

	public ArrayList<Record> loadDetails() throws Exception {

		ArrayList<Record> modelClasses = new ArrayList<Record>();
		BufferedReader reader = null;

		InputStream inputStream = mContext.openFileInput(mFilename);
		reader = new BufferedReader(new InputStreamReader(inputStream));

		StringBuilder json_string = new StringBuilder();
		String line = null;

		while ((line = reader.readLine()) != null) {
			json_string.append(line);
		}

		JSONArray jsonArray = (JSONArray) new JSONTokener(
				json_string.toString()).nextValue();

		for (int i = 0; i < jsonArray.length(); i++) {
			modelClasses.add(new Record(jsonArray.getJSONObject(i)));
		}

		if (reader != null)
			reader.close();

		return modelClasses;
	}
}