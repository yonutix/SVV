package com.example.mobileapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantInfo {
    String id;
    String name;
    String cuisine;
    int freeSpots;
    List<Grade> grades;
    JSONArray arrayGrades;

    public RestaurantInfo() {
    }

    public RestaurantInfo(String json) {
        try {
            name = (new JSONObject(json)).getString("name");
            cuisine = (new JSONObject(json)).getString("cuisine");
            freeSpots = (new JSONObject(json)).getInt("freeSpots");
            id = (new JSONObject(json)).getString("_id");

            arrayGrades = (new JSONObject(json)).getJSONArray("grades");
            grades = new ArrayList<Grade>();
            for (int i = 0; i < arrayGrades.length(); i++) {
                JSONObject gradeJson = arrayGrades.getJSONObject(i);

                grades.add(new Grade(gradeJson));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject serialiser() {
        Map<String, Object> output = new HashMap<String, Object>();
        output.put("_id", id);
        output.put("name", name);
        output.put("cuisine", cuisine);
        output.put("freeSpots", freeSpots);
        output.put("grades", arrayGrades);

        return new JSONObject(output);
    }

    public void save() {

    }
}
