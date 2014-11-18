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
    private Context mNormalContext;
    private String mNormalFilename;

    public JSONSerializer(Context cNormal, String fnameNormal) {
        mNormalContext = cNormal;
        mNormalFilename = fnameNormal;
    }

    public void saveDetails(ArrayList<Normal_Record> mRecord)
            throws JSONException, IOException {
        JSONArray jsonArray = new JSONArray();

        for (Normal_Record nr : mRecord) {
            jsonArray.put(nr.toJsonObject());
        }

        Writer writer = null;

        OutputStream outputStream = mNormalContext.openFileOutput(mNormalFilename, Context.MODE_PRIVATE);
        writer = new OutputStreamWriter(outputStream);
        writer.write(jsonArray.toString());

        if (writer != null) {
            writer.close();
        }
    }

    public ArrayList<Normal_Record> loadDetails() throws Exception {

        ArrayList<Normal_Record> mRecord = new ArrayList<Normal_Record>();
        BufferedReader reader = null;

        InputStream inputStream = mNormalContext.openFileInput(mNormalFilename);
        reader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder json_string = new StringBuilder();
        String line = null;

        while ((line = reader.readLine()) != null) {
            json_string.append(line);
        }

        JSONArray jsonArray = (JSONArray) new JSONTokener(
                json_string.toString()).nextValue();

        for (int i = 0; i < jsonArray.length(); i++) {
            mRecord.add(new Normal_Record(jsonArray.getJSONObject(i)));
        }

        if (reader != null)
            reader.close();

        return mRecord;
    }
}
