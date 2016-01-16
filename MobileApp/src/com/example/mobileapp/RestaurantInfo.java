package com.example.mobileapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantInfo {
    String name;
    String cuisine;
    int freeSpots;
    List<Grade> grades;

    public RestaurantInfo(String json){
        try {
            name = (new JSONObject(json)).getString("name");
            cuisine = (new JSONObject(json)).getString("cuisine");
            freeSpots = (new JSONObject(json)).getInt("freeSpots");

            JSONArray arrayGrades = (new JSONObject(json)).getJSONArray("grades");
            grades = new ArrayList<>();
            for(int i=0;i<arrayGrades.length(); i++){
                JSONObject gradeJson = arrayGrades.getJSONObject(i);

                grades.add(new Grade(gradeJson));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject serialiser(){
        Map<String, Object> output = new HashMap<>();
        output.put("name", name);
        output.put("cuisine", cuisine);
        output.put("freeSpots", freeSpots);

        return new JSONObject(output);
    }
}
