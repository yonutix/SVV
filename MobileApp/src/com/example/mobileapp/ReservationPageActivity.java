package com.example.mobileapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class ReservationPageActivity extends Activity {

	
	private DatePickerDialog.OnDateSetListener datePickerListener;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reservation_page);
		
		
		datePickerListener = new DatePickerDialog.OnDateSetListener() {

			// when dialog box is closed, below method will be called.
			public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {

			}
		};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reservation_page, menu);
		return true;
	}
	
	public void onDatePickerClicked(View v){
	}
	
	public void onCompleteReservationPressed(View v){
		Button completeReservation = (Button)findViewById(R.id.complete_reservation);
		completeReservation.setEnabled(false);
		if(GlobalDBConnection.getInst().confirmRegistration()){
			Log.v("Complete reservation", "yonutix");
			TextView confirmReservationResponse = (TextView)findViewById(R.id.reservation_confirmed);
			confirmReservationResponse.setVisibility(View.VISIBLE);
		}
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
}
