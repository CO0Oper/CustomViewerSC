package com.example.android.customviewer;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class QueryItems {

    public QueryItems() {
    }

    public static ArrayList<Collections> fetchData(String jOut) {

//        URL url = createUrl(requestUrl);
//
//        String jsonOutput = null;
//
//        try {
//            jsonOutput = makeHttpRequest(url);
//        } catch (IOException e) {
//        }

        ArrayList<Collections> collec = extractCollectionFromJson(jOut);
        return collec;
    }

    /**
     * Make a http request try to connect.
     * @param url
     * @return
     * @throws IOException
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonOutput = "";

        if (url == null) {
            return jsonOutput;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonOutput = readInputStream(inputStream);
            }
        } catch (IOException e) {
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        } return jsonOutput;
    }

    @SuppressLint("LongLogTag")
    private static URL createUrl(String stringUrl){
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e){
        }
        return url;
    }

    private static String readInputStream(InputStream in) throws IOException {
        StringBuilder output = new StringBuilder();
        if ( in != null) {
            InputStreamReader inReader = new InputStreamReader(in);
            BufferedReader buReader = new BufferedReader(inReader);
            String line = buReader.readLine();

            while (line != null) {
                output.append(line);
                line = buReader.readLine();
            }
        }return output.toString();
    }//End of method

    private static ArrayList<Collections> extractCollectionFromJson(String collectionJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(collectionJSON)) {
            return null;
        }
        ArrayList<Collections> collecions = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(collectionJSON);

            JSONArray collectionArray = baseJsonResponse.getJSONArray("custom_collections");

            // If there are results in the features array
            for (int i = 0 ; i < collectionArray.length() ; i++) {
                // Extract out the first feature (which is an earthquake)
                JSONObject collects = collectionArray.getJSONObject(i);
                JSONObject src = collects.getJSONObject("image");

                long c_id = collects.getLong("id");

                String handle = collects.getString("handle");

                String title = collects.getString("title");

                String body = collects.getString("body_html");

                String imageUrl = src.getString("src");

                Collections collec = new Collections(c_id, handle, title, imageUrl, body);

                collecions.add(collec);

                // Create a new {@link Event} object

            }
        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }
        return collecions;
    }

}
