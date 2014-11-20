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

public class JSONSerializer {
    private Context mContext;
    private String mFilename;
    private int jsonArrayCount;

    public JSONSerializer(Context c, String fname) {
        mContext = c;
        mFilename = fname;
    }

    public void saveDetails(ArrayList<Record> mRecord) throws JSONException,
            IOException {
        JSONArray jsonArray = new JSONArray();

        for (Record r : mRecord) {
            jsonArray.put(r.toJsonObject());
        }

        Writer writer = null;

        OutputStream outputStream = mContext.openFileOutput(mFilename,
                Context.MODE_PRIVATE);
        writer = new OutputStreamWriter(outputStream);
        writer.write(jsonArray.toString());
        if (writer != null) {
            writer.close();
        }

    }

    public ArrayList<Record> loadDetails() throws Exception {

        ArrayList<Record> mRecord = new ArrayList<Record>();
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
        jsonArrayCount = jsonArray.length();
        for (int i = 0; i < jsonArrayCount; i++) {
            mRecord.add(new Record(jsonArray.getJSONObject(i)));
        }

        if (reader != null) {
            reader.close();
        }
        return mRecord;

    }
}