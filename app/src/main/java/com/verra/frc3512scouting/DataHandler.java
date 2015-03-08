package com.verra.frc3512scouting;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class DataHandler {
    private String m_submitURL;
    private Vector<HashMap<String, Object>> m_storedData;
    private Context m_ctx;

    public DataHandler(Context ctx, String submitURL) {
        m_ctx = ctx;
        setSubmitURL(submitURL);

        m_storedData = decodeData(readStorageText());
    }

    public void setSubmitURL(String submitURL) {
        m_submitURL = submitURL;
    }

    public boolean storeData(HashMap<String, Object> data) {
        // Tack on the new data
        m_storedData.add(data);

        // JSONify everything
        return writeArrayData();
    }

    public int countRows() {
        return m_storedData.size();
    }

    public boolean clearData() {
        m_storedData = new Vector<>();
        return writeArrayData();
    }

    private boolean writeArrayData() {
        String storeDataString = encodeData(m_storedData);
        if(storeDataString == null) {
            return false;
        }
        return writeStorageText(storeDataString);
    }

    private String readStorageText() {

        try {
            InputStream in = m_ctx.openFileInput("FRCScoutingData.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            reader.close();
            return out.toString();

        } catch (IOException e) {
            return "";
        }
    }

    private boolean writeStorageText(String storeDataString) {
        // Open the file
        FileOutputStream fos;
        try {
            fos = m_ctx.openFileOutput("FRCScoutingData.txt", Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            return false;
        }

        // Write to the file
        try {
            fos.write(storeDataString.getBytes("UTF-8"));
            fos.close();
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    private Vector<HashMap<String, Object>> decodeData(String storedDataString) {
        // OK..
        JSONArray jsonarr;

        // This should be a JSON array
        try {
            jsonarr = new JSONArray(storedDataString);
        } catch (JSONException e) {
            // Return an empty vector if parsing failed
            return new Vector<>();
        }

        // Decode the magic number
        String magic;
        try {
            magic = jsonarr.getString(0);
        } catch (JSONException e) {
            // Return an empty vector if parsing failed
            return new Vector<>();
        }

        // Check the magic number
        if(!magic.equals("magic v0.1")) {
            // Return an empty vector if the magic number was wrong
            return new Vector<>();
        }

        // Everything else should be a map
        Vector<HashMap<String, Object>> data = new Vector<>();
        for(int i = 0; i < jsonarr.length(); i++) {
            JSONObject obj;
            try {
                obj = jsonarr.getJSONObject(i);
            } catch (JSONException e) {
                // Return an empty vector if parsing failed
                return new Vector<>();
            }

            HashMap<String, Object> hashmap = new HashMap<>();
            for(Iterator<String> it = obj.keys(); it.hasNext();) {
                String key =  it.next();
                try {
                    hashmap.put(key, obj.get(key));
                } catch (JSONException e) {
                    // I don't think this can happen ..
                    return new Vector<>();
                }
            }
            data.add(hashmap);
        }

        return data;
    }

    private String encodeData(Vector<HashMap<String, Object>> storedDataVector) {
        JSONArray arr = new JSONArray();

        arr.put("magic v0.1");

        for (HashMap<String, Object> hashmap : storedDataVector) {
            JSONObject jsonobj = new JSONObject();

            for (Map.Entry<String, Object> entry : hashmap.entrySet()) {
                try {
                    jsonobj.put(entry.getKey(), entry.getValue());
                } catch (JSONException e) {
                    // This is bad. We were given an object we can't encode
                    // It is important to check for this when calling encodeData()
                    return null;
                }
            }

            arr.put(jsonobj);
        }

        return arr.toString();
    }

    public void sendData() {

    }
}
