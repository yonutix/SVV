package com.example.mobileapp;

import java.util.Random;

public class RestaurantEntry {
	String restaurantName;
	String mId;
	Random r = new Random();
	
	public RestaurantEntry(){
		restaurantName = "Restaurant" + r.nextInt();
		mId = "" + r.nextInt();
	}
	
	public String getName(){
		return restaurantName;
	}
	
	public String getId(){
		return mId;
	}
	
}
