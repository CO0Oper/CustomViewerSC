package com.example.android.customviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends ArrayAdapter<Collections> {

    public ListAdapter(Context context, List<Collections> collections) {
        super(context, 0 , collections);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View titleListView = convertView;

        if(titleListView == null) {
            titleListView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_container, parent, false
            );
        }

        Collections currentCollection = getItem(position);

        TextView title = titleListView.findViewById(R.id.collec_title);

        String currentTitle = currentCollection.getTitle();

        title.setText(currentTitle);

        return titleListView;
    }

}
