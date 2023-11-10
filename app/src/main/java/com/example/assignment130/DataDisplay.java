package com.example.assignment130;

public class DataDisplay {
    String id;
    String name;
    String number;
    String gender;

    //constructor to store all our data we want to show from DB
    public DataDisplay(String data1, String data2, String data3, String data4){
        id = data1;
        name = data2;
        number = data3;
        gender = data4;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getGender() {
        return gender;
    }
}
