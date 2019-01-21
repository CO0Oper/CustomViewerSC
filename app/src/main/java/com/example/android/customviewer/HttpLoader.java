//package com.example.android.customviewer;
//
//import android.os.AsyncTask;
//
//import java.io.IOException;
//import java.net.URL;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//public class HttpLoader extends AsyncTask<URL, Integer, Long> {
//
//    private String url;
//
//    @Override
//    protected Long doInBackground(URL... urls) {
//
//            //final ArrayList<Collections> end2 = new ArrayList<>();
//
//            OkHttpClient client = new OkHttpClient();
//
//            Request request = new Request.Builder().url(url).build();
//
//            client.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
////                loadingIndicator.setVisibility(View.GONE);
////
////                mTextView.setText(R.string.no_internet);
//                }
//
//                @Override
//                public void onResponse(Call call, final Response response) throws IOException {
//                    if (response.isSuccessful()) {
//                        final String myResponse = response.body().string();
//
//
//                                //collec = QueryItems.fetchData(myResponse);
//                                if (myResponse != null) {
//                                    //end = myResponse;
//                                    //ArrayList<Collections> collec;
//                                    QueryItems qq = new QueryItems();
//                                    colleccc = qq.fetchProductID(myResponse);
//
//
//                                    String a = colleccc.get(0).getpId();
//
//                                    mTextView.setText(a);
//
//                                    //Collections c = new Collections(collec.get(0).getpId());
//                                    //end2.add(c);
//
////                                mAdapter = new ListAdapter(getBaseContext(), collec);
////                                collectionList.setAdapter(mAdapter);
//                                    response.close();
//
//                                }
//
//                    }//end of if
//                }//end of onResponse.
//            });//end of request
//
//            //return end2;
//
//
//        return null;
//    }
//}
