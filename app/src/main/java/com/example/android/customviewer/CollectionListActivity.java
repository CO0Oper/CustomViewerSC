package com.example.android.customviewer;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CollectionListActivity extends AppCompatActivity{

    private static final String REST_API =
            "https://shopicruit.myshopify.com/admin/custom_collections.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";

    private static TextView mTextView;

    private ListAdapter mAdapter;

    private View loadingIndicator;

    private ListView collectionList;

    private ArrayList<Collections> collec = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_list);

        collectionList = findViewById(R.id.collec_list);

        mTextView = findViewById(R.id.collec_text);

        collectionList.setEmptyView(mTextView);
        //loadingIndicator = findViewById(R.id.loading_indicator);

            queryRequest(REST_API);

//        ConnectivityManager connectManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        NetworkInfo networkInfo = connectManager.getActiveNetworkInfo();

//        if(networkInfo == null && !networkInfo.isConnected()){
//
//            loadingIndicator.setVisibility(View.GONE);
//
//            mTextView.setText(R.string.no_internet);
//        } else {

        //}

        collectionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Collections collec = mAdapter.getItem(position);

                String collc_id = collec.getcId();

                QueryItems q = new QueryItems();

                queryRequest(q.urlBuilder(1, collc_id));

                String Image = collec.getImage();


                Intent detailsIntent = new Intent(CollectionListActivity.this, DetailsActivity.class);

                detailsIntent.putExtra("collc_id", collc_id);

                detailsIntent.putExtra("Image", Image);

                startActivity(detailsIntent);
            }
        });

    }


//    private class httpRequest extends AsyncTask<URL, Integer, Long> {
//        protected ArrayList<Collections> doInBackground(URL... urls) {
//
//            OkHttpClient client = new OkHttpClient();
//
//            Request request = new Request.Builder().url(REST_API).build();
//
//            String myResponse = "";
//
//            Response response = null;
//            try {
//                response = client.newCall(request).execute();
//                myResponse = response.body().string();
//
//            } catch (IOException e) {
//
//            }
//
//            if (myResponse != null) {
//                QueryItems qq = new QueryItems();
//                collec = qq.fetchData(myResponse);
////                mAdapter = new ListAdapter(getBaseContext(), collec);
////                collectionList.setAdapter(mAdapter);
////                                long ll = collec.get(0).getcId();
////                                mTextView.setText("ID: " + ll);
//
//            }
//
//
//            return collec;
//            }
//
//
//
//
//
//        protected void onProgressUpdate(Integer... progress) {
//
//        }
//
//        protected void onPostExecute(Long result) {
//
//        }
//    }

//
//    public static List<Collections> fetchData(String requestUrl) {
//
//
//
//        List<Collections> collec = extractCollectionFromJson(jsonOutput);
//        return collec;
//    }

    private void queryRequest(String url){


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                loadingIndicator.setVisibility(View.GONE);
//
//                mTextView.setText(R.string.no_internet);
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    CollectionListActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //collec = QueryItems.fetchData(myResponse);
                            if(myResponse!=null) {
                                QueryItems qq = new QueryItems();
                                    collec = qq.fetchData(myResponse);
                                    mAdapter = new ListAdapter(getBaseContext(), collec);
                                    collectionList.setAdapter(mAdapter);


                                response.close();
//                                long ll = collec.get(0).getcId();
//                                mTextView.setText("ID: " + ll);

                            }

                        }
                    });
                }//end of if
            }//end of onResponse.
        });//end of request
    }


}
