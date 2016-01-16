package com.example.mobileapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	EditText email_textfield;
	EditText password_textfield;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		email_textfield = (EditText)findViewById(R.id.email);
		password_textfield = (EditText)findViewById(R.id.password);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onRegisterButtonPressed(View v){
		Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
		startActivity(i);
	}
	
	public void onLoginButton(View v){
		
		GlobalDBConnection.UserType login_result;
		login_result = GlobalDBConnection.getInst().tryLogin(email_textfield.getText().toString(), password_textfield.getText().toString());
		if (login_result == GlobalDBConnection.UserType.NONE) {
			TextView credential_validation = (TextView)findViewById(R.id.credentials_validation);
			credential_validation.setVisibility(View.VISIBLE);
		}

		if (login_result == GlobalDBConnection.UserType.CLIENT) {
			Intent result = new Intent(MainActivity.this, NormalUserSearchList.class);
			result.putExtra("username", GlobalDBConnection.getInst().getEmail());
			result.putExtra("password", GlobalDBConnection.getInst().getPassword());
			startActivity(result);
		}

		if (login_result == GlobalDBConnection.UserType.MANAGER) {
			Intent i = new Intent(getApplicationContext(), RestaurantManagerActivity.class);
			i.putExtra("managerEmail", email_textfield.getText().toString());
			startActivity(i);
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
