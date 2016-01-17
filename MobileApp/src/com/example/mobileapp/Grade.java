package com.example.mobileapp;

import org.json.JSONException;
import org.json.JSONObject;

public class Grade {

    String grade;
    int score;

    public Grade(){}
    public Grade(JSONObject json){
        try {
            grade = json.getString("grade");
            score = json.getInt("score");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
