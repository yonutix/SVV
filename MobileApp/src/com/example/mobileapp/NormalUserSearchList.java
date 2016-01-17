package com.example.mobileapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;


class OnAppListListener implements OnItemClickListener{

    Activity context;

    public OnAppListListener(Activity context) {
       this.context = context;
       
       
    }

    @Override 
    public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3)
    {
    	Intent result = new Intent(context, RestaurantDetailsActivity.class);
		result.putExtra("username", GlobalDBConnection.getInst().getEmail());
		result.putExtra("password", GlobalDBConnection.getInst().getPassword());
		result.putExtra("id", GlobalDBConnection.getInst().getRestaurants().get(position).getId());
		context.startActivity(result);
    }

 }


public class NormalUserSearchList extends Activity implements OnItemSelectedListener{
	
	ListView chooseRestaurant;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_normal_user_search_list);
		
		chooseRestaurant = (ListView) findViewById(R.id.listview);
		
	
		
		RestaurantListAdapter appListAdapter = new RestaurantListAdapter(this, 
				GlobalDBConnection.getInst().getRestaurantNames(), 
				GlobalDBConnection.getInst().getRestaurants());
		
		chooseRestaurant.setAdapter(appListAdapter);
		chooseRestaurant.setOnItemClickListener(new OnAppListListener(this));
		
		
		
		
		Spinner spinner = (Spinner) findViewById(R.id.city_spinner);

		// Spinner click listener
		spinner.setOnItemSelectedListener(this);

		// Spinner Drop down elements
		List<String> categories = new ArrayList<String>();
		categories.add("Bucuresti");

		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				categories);

		// Drop down layout style - list view with radio button
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		spinner.setAdapter(dataAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.normal_user_search_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}
