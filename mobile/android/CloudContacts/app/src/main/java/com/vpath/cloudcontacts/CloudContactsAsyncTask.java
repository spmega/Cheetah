package com.vpath.cloudcontacts;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

/**
 * Created by spmega4567 on 8/2/16.
 */
public class CloudContactsAsyncTask extends AsyncTask<URL, Void, JSONObject> {

    @Override
    protected JSONObject doInBackground(URL... urls) {
        InputStream stream = null;
        BufferedReader rd = null;
        try {
            stream = urls[0].openStream();
            rd =  new BufferedReader(new InputStreamReader(stream));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
