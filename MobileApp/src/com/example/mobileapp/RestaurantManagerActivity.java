package com.example.mobileapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;


public class RestaurantManagerActivity extends Activity {

    ListView gradesList;
    TextView restaurantNameView;
    TextView cuisine;
    EditText tablesNo;
    RestaurantInfo restaurant;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_manager);

        Log.v("mada", "managerEmail");

        String managerEmail = getIntent().getExtras().getString("managerEmail");

        String restaurantName = "Calif";
        restaurant = GlobalDBConnection.getInst().getRestaurantInfo(restaurantName);


        restaurantNameView = (TextView) findViewById(R.id.restaurantName);
        restaurantNameView.setText(restaurantName);
        cuisine = (TextView) findViewById(R.id.restaurantCuisine);
        cuisine.setText(restaurant.cuisine);
        tablesNo = (EditText) findViewById(R.id.tablesNo);
        tablesNo.setText("" + restaurant.freeSpots);

        gradesList = (ListView) findViewById(R.id.listViewGrades);
        String[] gradeSum = new String[restaurant.grades.size()];
        for (int i = 0; i < restaurant.grades.size(); i++) {
            gradeSum[i] = "Review " + i;
        }
        gradesList.setAdapter(new GradesListAdapter(this, gradeSum, new Vector<Grade>(restaurant.grades)));
    }

    public void onOkButtonPressed(View v) {
        Intent result = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(result);
    }

    public void onEditButtonPressed(View v) {

        Toast.makeText(getApplicationContext(), "Restaurant details saved", Toast.LENGTH_SHORT).show();
        restaurant.save();
    }

}