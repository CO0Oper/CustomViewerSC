package com.example.android.customviewer;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

public class HttpLoader extends AsyncTaskLoader<List<Collections>> {

    private String url;

    public HttpLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Collections> loadInBackground() {
        if (url == null) {
            return null;
        }

        List<Collections> collec = QueryItems.fetchData(url);
        return collec;
    }
}
