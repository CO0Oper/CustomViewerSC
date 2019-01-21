//package com.example.android.customviewer;
//
//import android.app.Activity;
//import android.support.v7.app.AppCompatActivity;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//public class QueryRequest  {
//
//    public QueryRequest () {
//    }
//
//    public ArrayList<Collections> fetchData(String url, Activity activity){
//        ArrayList<Collections> array = new ArrayList<>();
//        queryRuquest(url, activity);
//        return array;
//
//    }
//    private ArrayList queryRuquest(String url,Activity activity) {
//        final ArrayList<Collections> end2 = new ArrayList<>();
//
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder().url(url).build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
////                loadingIndicator.setVisibility(View.GONE);
////
////                mTextView.setText(R.string.no_internet);
//            }
//
//            @Override
//            public void onResponse(Call call, final Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    final String myResponse = response.body().string();
//
//                    DetailsActivity.this.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            //collec = QueryItems.fetchData(myResponse);
//                            if (myResponse != null) {
//                                //end = myResponse;
//                                ArrayList<Collections> collec;
//                                QueryItems qq = new QueryItems();
//                                collec = qq.fetchProductID(myResponse);
//
//                                Collections c = new Collections(collec.get(0).getpId());
//                                end2.add(c);
//
////                                mAdapter = new ListAdapter(getBaseContext(), collec);
////                                collectionList.setAdapter(mAdapter);
//                                response.close();
//
//
//                            }
//                        }
//                    });
//                }//end of if
//            }//end of onResponse.
//        });//end of request
//
//        return end2;
//    }
//}
