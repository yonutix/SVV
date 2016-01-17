package com.example.mobileapp;


import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Vector;

public class GradesListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    Vector<Grade> mEntries;
    private final String[] appNames;



    public GradesListAdapter(Activity context, String[] web, Vector<Grade> entries) {
        super(context, R.layout.grade_item, web);
        this.context = context;
        mEntries = entries;
        this.appNames = web;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.grade_item, null, true);

        TextView txtTitle = (TextView)rowView.findViewById(R.id.reviewGrade);
        txtTitle.setText("Grade: " + mEntries.get(position).grade);

        TextView addressTxt = (TextView) rowView.findViewById(R.id.score);
        addressTxt.setText("Score: " + mEntries.get(position).score);
        return rowView;
    }
}
