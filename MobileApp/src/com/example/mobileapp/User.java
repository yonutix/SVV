package com.example.mobileapp;


public class User {

    String name;
    String email;
    String password;
    String phoneNo;
    GlobalDBConnection.UserType manager;

    public User(String name, String email, String password, String phoneNo, GlobalDBConnection.UserType manager){
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
        this.manager = manager;
    }

    private String serialise(){

        return "{\"username\":\""+name+"\", \"email\":\""+email+"\", \"phone\":\""+phoneNo
                + "\", \"password\":\"" + password +"\", \"type\":\""+manager.ordinal()+"\"}";

    }
    public boolean register() {

        String serialised = serialise();
        return false;
    }
}

