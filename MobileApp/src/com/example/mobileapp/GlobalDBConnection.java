package com.example.mobileapp;

import android.util.Log;

public class GlobalDBConnection {
	
	static GlobalDBConnection singleton = null;
	
	enum UserType{NONE, CLIENT, MANAGER};
	
	public String email;
	public String password;
	public UserType userType;
	
	public GlobalDBConnection(){
		email = new String();
		password = new String();
		
	}
	
	public UserType tryLogin(String email, String password){
		//If request succeeds
		Log.v("Login attempt with" + email + "  " + password, "yonutix");
		this.email = email;
		this.password = password;
		return UserType.CLIENT;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getPassword(){
		return password;
	}
	
	
	
	//-------------------------------------------------
	//Sarch restaurants
	
	
	
	public static GlobalDBConnection getInst(){
		if (singleton == null){
			singleton = new GlobalDBConnection();
		}
		
		return singleton;
	}
}
