package com.example.mobileapp;


public class User {

    String name;
    String email;
    String password;
    String phoneNo;
    String manager = GlobalDBConnection.UserType.NONE.name();

    public User(String name, String email, String password, String phoneNo, GlobalDBConnection.UserType manager){
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
        this.manager = manager.name();
    }


    public boolean register() {
        return  GlobalDBConnection.getInst().createUser(this);
    }
}

