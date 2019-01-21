package com.example.android.customviewer;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DetailsActivity extends Activity {

    private TextView mTextView1;

    private ImageView mImageView;

    private String collection_id;

    private String imageUrl;

    //private String finalUrl;

    //private static ArrayList<Collections> collections = new ArrayList<>();

    //private static ArrayList<Collections> products = new ArrayList<>();

    private Collections collectiono;

    private static String contains;

    //private String urrl = "https://shopicruit.myshopify.com/admin/products.json?ids=2759175875,2759163011,2759146883,2759145347,2759138307,2759134083,2759128323,2759127171,2759125827,2759124675&page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";

    private CallBack task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if(task != null && task.getStatus() == AsyncTask.Status.RUNNING){
            task.cancel(true);
        }

        //Retrieve data from intent.
        Intent intent = getIntent();
        collection_id = intent.getStringExtra("collc_id");
        imageUrl = intent.getStringExtra("Image");
        //finalUrl = intent.getStringExtra("finalQuery");


        mTextView1 = findViewById(R.id.details_tags);
        //mTextView1.setText("Aluminum, Gorgeous, Watch");

//        private TextView mTextView2;
//        private TextView mTextView3;
//        private TextView mTextView4;
//        private TextView mTextView5;
//        private TextView mTextView6;
//
//        mTextView2 = findViewById(R.id.detail_body);
//            mTextView2.setText("Visualize end-to-end action-items");
//
//        mTextView3 = findViewById(R.id.details_vendor);
//            mTextView3.setText("Monahan-Christiansen");
//
//        mTextView4 = findViewById(R.id.details_inventory);
//            mTextView4.setText("3625");
//
//        mTextView5 = findViewById(R.id.details_type);
//            mTextView5.setText(	"Gloves");
//
//        mTextView6 = findViewById(R.id.details_name);
//            mTextView6.setText("Gorgeous Cotton Gloves");


          mImageView = findViewById(R.id.details_image);
//        mTextView.setText("Id:" + collection_id );

        Picasso.get().load(imageUrl).into(mImageView);

        //queryRequest(urrl);

//
//
//        String queryProductID;
//
//        //colleccc = queryRuquest(urlBuilder(1, collection_id));
//        String a = colleccc.get(0).getpId();
//        mTextView.setText(a);
        QueryItems qobj = new QueryItems();

        String urll = qobj.urlBuilder(1, collection_id);

        //String temp = null;



        try {
            task = new CallBack();
            contains = task.execute(urll).get();
        } catch (ExecutionException e) {
        } catch (InterruptedException e) {
        }

        ArrayList<Collections> collections;
            QueryItems second = new QueryItems();
            collections = QueryItems.fetchProductID(contains);

            String finalUrl = second.urlBuilder(2, collections.get(0).getpId());

            queryRequest(finalUrl);
            //mTextView1.setText(finalUrl);
            //queryRequest(finalUrl);

//            try {
//                task = new CallBack();
//                task.execute(finalUrl).get();
//            } catch (ExecutionException e) {
//            } catch (InterruptedException e) {
//            }


        }

//            QueryItems third = new QueryItems();
//            products = third.fetchProductResults(contains);
            //products.get(0).getInventory()
            //mTextView.setText(contains);



//    private String urlBuilder(int option, String id) {
//        String query = "";
//        switch (option) {
//            case 1:
//                query = "https://shopicruit.myshopify.com/admin/collects.json?collection_id=";
//                query += id;
//                query += "&page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";
//                break;
//            case 2:
//                query = "https://shopicruit.myshopify.com/admin/products.json?ids=";
//                query += id;
//                query += "&page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";
//                break;
//        }
//        return query;
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
                    final String myResponserr = response.body().string();
                    DetailsActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //collec = QueryItems.fetchData(myResponse);
                            if(myResponserr!=null) {
                                //ArrayList<Collections> products
                                ArrayList<Collections> col;
                                QueryItems qq = new QueryItems();

                                    //col = QueryItems.fetchData(myResponserr);

                                QueryItems third = new QueryItems();
                                //col = third.fetchProductResults(myResponserr);
                                col = third.FromJson(myResponserr);
                                mTextView1.setText(col.size());


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



    class CallBack extends AsyncTask<String, Void, String> {

        String myResponsee;

        public CallBack() {
            super();
        }

        @Override
        protected String doInBackground(String... strings) {

            if(isCancelled()) return null;

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url(strings[0]).build();

            Response response = null;
            try {
                response = client.newCall(request).execute();
                //return response.body().string();
                myResponsee = response.body().string();
                if (myResponsee != null) {

                    //contains = myResponsee;

                    String contains2 = myResponsee;

                    response.close();
                    //return myResponse;
                }
            } catch (IOException e) {
            }
            return myResponsee;
        }


        @Override
        protected void onPostExecute(String result){

        }

        protected void onProgressUpdate(Integer... progress) {
            if(isCancelled()) return;
        }


    }


}


//