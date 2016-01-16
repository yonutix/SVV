package com.example.mobileapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;


class OnAppListListener implements OnItemClickListener{

	NormalUserSearchList context;

    public OnAppListListener(NormalUserSearchList context) {
       this.context = context;
       
       
    }

    @Override 
    public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3)
    {
    	Intent result = new Intent(context, RestaurantDetailsActivity.class);
		result.putExtra("username", GlobalDBConnection.getInst().getEmail());
		result.putExtra("password", GlobalDBConnection.getInst().getPassword());
		context.startActivity(result);
    }

 }

class KitchenSpecificListener implements OnItemSelectedListener{
	Context mContext;
	public KitchenSpecificListener(Context context){
		mContext = context;
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


public class NormalUserSearchList extends Activity implements OnItemSelectedListener{
	
	ListView chooseRestaurant;
	
	List<String> kitchenCategories;
	
	Spinner kitchenSpinner;
	
	KitchenSpecificListener kitchenSpecificListener;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_normal_user_search_list);
		
		kitchenSpinner = (Spinner) findViewById(R.id.kitchen_profile);
		kitchenCategories = new ArrayList<String>();
		
		kitchenCategories.add("American ");
		kitchenCategories.add("Irish");
		kitchenCategories.add("French");
		kitchenCategories.add("Delicatessen");
		kitchenCategories.add("Hamburgers");
		kitchenCategories.add("Ice Cream, Gelato, Yogurt, Ices");
		kitchenCategories.add("Chinese");
		kitchenCategories.add("Bakery");
		kitchenCategories.add("Turkish");
		kitchenCategories.add("Caribbean");
		kitchenCategories.add("Chicken");
		kitchenCategories.add("Donuts");
		kitchenCategories.add("Bagels/Pretzels");
		kitchenCategories.add("Continental");
		kitchenCategories.add("Pizza");
		kitchenCategories.add("Steak");
		kitchenCategories.add("Italian");
		kitchenCategories.add("German");
		kitchenCategories.add("Sandwiches/Salads/Mixed Buffet");
		
		kitchenSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				kitchenCategories));
		kitchenSpinner.setOnItemSelectedListener(kitchenSpecificListener);
		
		kitchenSpecificListener = new KitchenSpecificListener(this);
		
		
		
		
		
	}
	
	public void onSubmitFilter(View v) {
		chooseRestaurant = (ListView) findViewById(R.id.listview);
		
		EditText name_text = (EditText)findViewById(R.id.restaurant_name_reservation);
		
		EditText spots_count = (EditText) findViewById(R.id.person_count_reservation);
		
		

		RestaurantListAdapter appListAdapter = new RestaurantListAdapter(this,
				GlobalDBConnection.getInst().getRestaurantNames(kitchenSpinner.getSelectedItem().toString(), name_text.getText().toString(), spots_count.getText().toString()),
				GlobalDBConnection.getInst().getRestaurants(kitchenSpinner.getSelectedItem().toString(), name_text.getText().toString(), spots_count.getText().toString()));

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
