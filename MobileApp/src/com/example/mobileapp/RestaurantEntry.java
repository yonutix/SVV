package com.example.mobileapp;

import java.util.Random;

public class RestaurantEntry {
	String restaurantName;
	String mId;
	String mAddress;
	Random r = new Random();
	
	public RestaurantEntry(){
		restaurantName = "Restaurant" + r.nextInt();
		mId = "" + r.nextInt();
	}
	
	public RestaurantEntry(String name, String address){
		restaurantName = name;
		mId = "" + r.nextInt();
		mAddress = address;
	}
	
	public String getName(){
		return restaurantName;
	}
	
	public String getAddress(){
		return mAddress;
	}
	
	public String getId(){
		return mId;
	}
	
}
