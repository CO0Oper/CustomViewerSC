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

        ArrayList<Collections> collec = extractCollectionFromJson(jOut);
        return collec;
    }

    public static ArrayList<Collections> fetchProductID(String jOut) {

        ArrayList<Collections> collec = extractProductsFromJson(jOut);
        return collec;
    }

    public  ArrayList<Collections> fetchProductResults(String jOut) {

        ArrayList<Collections> collec = FromJson(jOut);
        return collec;
    }

    public String urlBuilder(int option, String id) {
        String query = "";
        switch (option) {
            case 1:
                query = "https://shopicruit.myshopify.com/admin/collects.json?collection_id=";
                query += id;
                query += "&page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";
                break;

            case 2:
                query = "https://shopicruit.myshopify.com/admin/products.json?ids=";
                query += id;
                query += "&page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";
                break;
        }
        return query;
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
                try{

                JSONObject baseJsonResponse = new JSONObject(collectionJSON);

                JSONArray collectionArray = baseJsonResponse.getJSONArray("custom_collections");


                // If there are results in the features array
                for (int i = 0; i < collectionArray.length(); i++) {
                    // Extract out the first feature (which is an earthquake)
                    JSONObject collects = collectionArray.getJSONObject(i);
                    JSONObject src = collects.getJSONObject("image");

                    String c_id = collects.getString("id");

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

    private static ArrayList<Collections> extractProductsFromJson(String collectionJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(collectionJSON)) {
            return null;
        }
        ArrayList<Collections> collecions = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(collectionJSON);
            JSONArray collectionArray = baseJsonResponse.getJSONArray("collects");
            String products_id = "";
            // If there are results in the features array
            for (int j = 0; j < collectionArray.length(); j++) {
                // Extract out the first feature (which is an earthquake)
                JSONObject collects = collectionArray.getJSONObject(j);
                products_id += collects.getString("product_id");
                if (j < collectionArray.length() - 1) {
                    products_id += ",";
                }
            }
            Collections collec = new Collections(products_id);
            collecions.add(collec);
        } catch (JSONException e) {
        }

        return collecions;

    }

    public  ArrayList<Collections> FromJson(String collectionJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(collectionJSON)) {
            return null;
        }
        ArrayList<Collections> collecions = new ArrayList<>();
        try{

            JSONObject baseJsonResponse = new JSONObject(collectionJSON);

            JSONArray collectionArray = baseJsonResponse.getJSONArray("products");

            // If there are results in the features array
            int inventory = 0;

            for (int i = 0; i < collectionArray.length(); i++) {
                // Extract out the first feature (which is an earthquake)
                JSONObject collects = collectionArray.getJSONObject(i);
                JSONObject src = collects.getJSONObject("variants");

                String title = collects.getString("id");

                String body = collects.getString("handle");

                String vendor = collects.getString("title");

                String type = collects.getString("body_html");

                String tags = collects.getString("src");

                inventory += src.getInt("inventory_quantity");
                Collections collec = new Collections(title, body, vendor, type, tags, inventory);
                collecions.add(collec);
                inventory = 0;
            }
        } catch (JSONException e) {
        }


        return collecions;
    }


}
