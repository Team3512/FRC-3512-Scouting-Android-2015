package com.verra.frc3512scouting;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

/**
 * Created by acf on 3/8/15.
 */
public class DataHandler {
    private String m_submitURL;
    private Context m_ctx;

    public DataHandler(Context ctx, String submitURL) {
        m_ctx = ctx;
        setSubmitURL(submitURL);
    }

    public void setSubmitURL(String submitURL) {
        m_submitURL = submitURL;
    }

    public boolean storeData(HashMap<String, Object> data) {
        // Get existing data
        String storedDataString = readStorageText();
        Vector<HashMap<String, Object>> storedDataVector = decodeData(storedDataString);

        // Tack on the new data
        storedDataVector.add(data);

        // JSONify everything
        String storeDataString = encodeData(storedDataVector);
        if(storeDataString == null) {
            return false;
        }

        // Store it to a file
        if(!writeStorageText(storeDataString)) {
            return false;
        }

        return true;
    }

    private String readStorageText() {
        String text;

        // Open the file
        FileInputStream fos;
        try {
            fos = m_ctx.openFileInput("FRCScoutingData.txt");
        } catch (FileNotFoundException e) {
            return "";
        }

        // TODO: Finish implementing this
        //fos.read

        return "";
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
            fos.write(storeDataString.getBytes());
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
            return new Vector<HashMap<String, Object>>();
        }

        // Decode the magic number
        String magic;
        try {
            magic = jsonarr.getString(0);
        } catch (JSONException e) {
            // Return an empty vector if parsing failed
            return new Vector<HashMap<String, Object>>();
        }

        // Check the magic number
        if(!magic.equals("magic v0.1")) {
            // Return an empty vector if the magic number was wrong
            return new Vector<HashMap<String, Object>>();
        }

        // Everything else should be a map
        Vector<HashMap<String, Object>> data = new Vector<HashMap<String, Object>>();
        for(int i = 0; i < jsonarr.length(); i++) {
            JSONObject obj;
            try {
                obj = jsonarr.getJSONObject(i);
            } catch (JSONException e) {
                // Return an empty vector if parsing failed
                return new Vector<HashMap<String, Object>>();
            }

            HashMap<String, Object> hashmap = new HashMap<String, Object>();
            for(Iterator<String> it = obj.keys(); it.hasNext();) {
                String key =  it.next();
                try {
                    hashmap.put(key, obj.get(key));
                } catch (JSONException e) {
                    // I don't think this can happen ..
                    return new Vector<HashMap<String, Object>>();
                }
            }
            data.add(hashmap);
        }

        return data;
    }

    private String encodeData(Vector<HashMap<String, Object>> storedDataVector) {
        JSONArray arr = new JSONArray();

        arr.put("magic v0.1");

        for(Iterator<HashMap<String, Object>> it = storedDataVector.iterator(); it.hasNext();) {
            HashMap<String, Object> hashmap = it.next();
            JSONObject jsonobj = new JSONObject();

            for(Iterator<Map.Entry<String, Object>> mit = hashmap.entrySet().iterator();
                mit.hasNext();) {
                Map.Entry<String, Object> entry = mit.next();
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
