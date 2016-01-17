package com.example.mobileapp;

import java.util.Vector;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RestaurantListAdapter extends ArrayAdapter<String> {
	private final Activity context;
	Vector<RestaurantEntry> mEntries;
	private final String[] appNames;
	
	
	
	public RestaurantListAdapter(Activity context, String[] web, Vector<RestaurantEntry> entries) {
		super(context, R.layout.list_item, web);
		this.context = context;
		mEntries = entries;
		this.appNames = web;
	}
	
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView= inflater.inflate(R.layout.list_item, null, true);
		
		TextView txtTitle = (TextView) rowView.findViewById(R.id.textView);
		txtTitle.setText(mEntries.get(position).restaurantName);
		return rowView;
	}
}
