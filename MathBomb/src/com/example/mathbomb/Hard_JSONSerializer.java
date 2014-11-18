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

public class Hard_JSONSerializer {
    private Context mHardContext;
    private String mHardFilename;

    public Hard_JSONSerializer(Context cHard, String fnameHard) {
        mHardContext = cHard;
        mHardFilename = fnameHard;
    }

    public void saveDetails(ArrayList<Hard_Record> mRecord)
            throws JSONException, IOException {
        JSONArray jsonArray = new JSONArray();

        for (Hard_Record er : mRecord) {
            jsonArray.put(er.toJsonObject());
        }

        Writer writer = null;

        OutputStream outputStream = mHardContext.openFileOutput(mHardFilename, Context.MODE_PRIVATE);
        writer = new OutputStreamWriter(outputStream);
        writer.write(jsonArray.toString());

        if (writer != null) {
            writer.close();
        }
    }

    public ArrayList<Hard_Record> loadDetails() throws Exception {

        ArrayList<Hard_Record> mRecord = new ArrayList<Hard_Record>();
        BufferedReader reader = null;

        InputStream inputStream = mHardContext.openFileInput(mHardFilename);
        reader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder json_string = new StringBuilder();
        String line = null;

        while ((line = reader.readLine()) != null) {
            json_string.append(line);
        }

        JSONArray jsonArray = (JSONArray) new JSONTokener(
                json_string.toString()).nextValue();

        for (int i = 0; i < jsonArray.length(); i++) {
            mRecord.add(new Hard_Record(jsonArray.getJSONObject(i)));
        }

        if (reader != null)
            reader.close();

        return mRecord;
    }
}