package com.example.mobileapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.StrictMode;
import android.util.Log;


class RequestThread extends Thread {

    HttpURLConnection conn;

    public RequestThread() {

    }


    public void run() {


        //conn.disconnect();
    }
}

public class GlobalDBConnection {

    static GlobalDBConnection singleton = null;


    public static RestaurantInfo getRestaurantInfo(String restaurantName) {

        RestaurantInfo mock = new RestaurantInfo();
        mock.name = restaurantName;
        mock.cuisine = "Continental";
        mock.freeSpots = new Random().nextInt(50);
        mock.grades = new ArrayList<Grade>();
        Grade g1 = new Grade();
        g1.grade = "Very nice place";
        g1.score = 5;
        mock.grades.add(g1);

        Grade g2 = new Grade();
        g2.grade = "Good food";
        g2.score = 5;
        mock.grades.add(g2);

        return mock;
    }

    enum UserType {NONE, CLIENT, MANAGER}

    ;

    public String email;
    public String password;
    public UserType userType;
    public static final String IP = "http://192.168.0.172:3000";


    public void makeRequest() {

    }

    private GlobalDBConnection() {
        email = new String();
        password = new String();

    }

    public UserType tryLogin(String email, String password) {
        //If request succeeds
        Log.v("Login attempt with" + email + "  " + password, "yonutix");
        this.email = email;
        this.password = password;
        return UserType.MANAGER;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    //-------------------------------------------------
    //Sarch restaurants

    public String[] getRestaurantNames(String cuisine, String name, String spots) {
        Vector<RestaurantEntry> res = getRestaurants(cuisine, name, spots);
        String[] result = new String[res.size()];
        for (int i = 0; i < res.size(); ++i) {
            result[i] = res.get(i).getName();
        }

        return result;
    }


    public Vector<RestaurantEntry> getRestaurants(String cuisine, String name, String spots) {

        Vector<RestaurantEntry> data = new Vector<RestaurantEntry>();

        try {
            Log.v("Start try", "yonutix");
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            HttpURLConnection con = (HttpURLConnection) (new URL(IP + "/restaurants")).openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", "application/json");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();
            Log.v("tag", "yonutix req: " + spots + "  " + cuisine + " " + name);
            Map<String, String> comment = new HashMap<String, String>();
            if (spots.length() > 0)
                comment.put("numSpots", spots);
            comment.put("cuisine", cuisine + " ");
            if (name.length() > 0)
                comment.put("name", name);


            JSONObject jsonX = new JSONObject(comment);
            String json = jsonX.toString();

            con.getOutputStream().write((json).getBytes());

            Log.v("" + con.getResponseCode(), "yonutix " + json);


            String str = "";
            StringBuffer buff = new StringBuffer();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while ((str = in.readLine()) != null) {
                buff.append(str);
            }
            con.disconnect();

            //Log.v("++++++", "yonutix " + buff.toString());
            JSONArray obj = new JSONArray(buff.toString());

            int count = obj.length();
            for (int i = 0; i < count; ++i) {
                JSONObject current = (JSONObject) obj.get(i);
                //Log.v("tag", "yonutix " + current);
                //streetText
                data.add(new RestaurantEntry(current.get("name").toString(), ((JSONObject) current.get("address")).get("street").toString()));

            }


        } catch (Exception e) {
            Log.v("Request" +
                    " could not be cofdfmpelted ", "yonutix" + e.getMessage());


            for (StackTraceElement ste : e.getStackTrace()) {
                Log.v("Request could not be cofdfmpelted ", "yonutix " + ste);
            }

        }


        //data.add(new RestaurantEntry());
        //data.add(new RestaurantEntry());
        //data.add(new RestaurantEntry());
        return data;
    }


    public boolean createUser(User user) {

        try {
            Log.v("Start try", "yonutix");
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            HttpURLConnection con = (HttpURLConnection) (new URL(IP + "/newuser")).openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", "application/json");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();
            Log.v("tag", "yonutix req: " + user.name + "  " + user.phoneNo + " " + user.manager);
            Map<String, String> comment = new HashMap<String, String>();
            comment.put("name", user.name);
            comment.put("email", user.email);
            comment.put("password", user.password);
            comment.put("phone", user.phoneNo);
            comment.put("type", user.manager.toLowerCase());


            JSONObject jsonX = new JSONObject(comment);
            String json = jsonX.toString();

            con.getOutputStream().write((json).getBytes());

            Log.v("" + con.getResponseCode(), "yonutix " + json);


            String str = "";
            StringBuffer buff = new StringBuffer();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while ((str = in.readLine()) != null) {
                buff.append(str);
            }
            con.disconnect();

            JSONObject obj = new JSONObject(buff.toString());

            return ("success".equals(obj.getString("response")));

        } catch (Exception e) {
            Log.v("Request" +
                    " could not be cofdfmpelted ", "yonutix" + e.getMessage());


            for (StackTraceElement ste : e.getStackTrace()) {
                Log.v("Request could not be cofdfmpelted ", "yonutix " + ste);
            }
        }
        return false;
    }

    public boolean book(String name, int numSpots) {

        try {
            Log.v("Start try", "yonutix");
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            HttpURLConnection con = (HttpURLConnection) (new URL(IP + "/book")).openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", "application/json");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();
            Log.v("tag", "yonutix req: " + name + "  " + numSpots);
            Map<String, Object> comment = new HashMap<String, Object>();
            comment.put("name", name);
            comment.put("numSpots", numSpots);


            JSONObject jsonX = new JSONObject(comment);
            String json = jsonX.toString();

            con.getOutputStream().write((json).getBytes());

            Log.v("" + con.getResponseCode(), "yonutix " + json);


            return true;

        } catch (Exception e) {
            Log.v("Request" +
                    " could not be cofdfmpelted ", "yonutix" + e.getMessage());


            for (StackTraceElement ste : e.getStackTrace()) {
                Log.v("Request could not be cofdfmpelted ", "yonutix " + ste);
            }
        }
        return false;
    }


    public boolean confirmRegistration() {
        return true;
    }


    public static GlobalDBConnection getInst() {
        if (singleton == null) {
            singleton = new GlobalDBConnection();
        }

        return singleton;
    }
}
