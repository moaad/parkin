package com.example.park_in;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

abstract class EmptySlotsTask extends AsyncTask<String, Void, String> {

    @SuppressLint("WrongThread")
    @Override
    public String doInBackground(String... args) {

        String outData = null;
        Log.d("SHLOMI", "EmptySlotsTask: start");
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL("https://umodyceboa.execute-api.us-east-2.amazonaws.com/default/lambda_handler");

            Log.d("SHLOMI", "EmptySlotsTask: url: https://umodyceboa.execute-api.us-east-2.amazonaws.com/default/lambda_handler");
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = null;
            in = new BufferedInputStream(urlConnection.getInputStream());

            JSONObject reader = new JSONObject(readStream(in));
            outData = reader.getString("available_spaces");
            Log.d("SHLOMI", "EmptySlotsTask: got " + outData + " empty spaces");
//                TextView mTextView = (TextView) findViewById(R.id.counter);
//                mTextView.setText("Empty slots: " + outData);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

        return outData;
    }
    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }
}