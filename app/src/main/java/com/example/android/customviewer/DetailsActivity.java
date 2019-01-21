package com.example.android.customviewer;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DetailsActivity extends Activity {

    private TextView mTextView;

    private String collection_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Retrieve data from intent.
        Intent intent = getIntent();
        collection_id = intent.getStringExtra("collc_id");

        mTextView = findViewById(R.id.details_name);
        mTextView.setText("Id:" + collection_id );

        OkHttpClient client = new OkHttpClient();
       // Request request = new Request().Builder.url(urlBuilder(collection_id)).build();





    }

    private String urlBuilder(String c_id) {
        String query = "https://shopicruit.myshopify.com/admin/collects.json?collection_id=";
        query += c_id;
        query += "68424532024&page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";
        return query;
    }

}
