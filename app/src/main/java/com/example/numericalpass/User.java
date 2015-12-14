package com.example.numericalpass;

/**
 * Created by Shreya Mehrishi on 12/13/2015.
 */
public class User {
    //private variables
    int id;
    String username;
    String password;
    int wrongtry;

    // Empty constructor
    public User() {
    }
    // getting ID
    public int getID(){
        return this.id;
    }

    // setting id
    public void setID(int id){
        this.id = id;
    }

    // getting username
    public String getUsername(){
        return this.username;
    }

    // setting username
    public void setUsername(String username){
        this.username = username;
    }
    //getting password
    public String getPassword(){
        return this.password;
    }

    // setting username
    public void setPassword(String password){
        this.password = password;
    }



}
