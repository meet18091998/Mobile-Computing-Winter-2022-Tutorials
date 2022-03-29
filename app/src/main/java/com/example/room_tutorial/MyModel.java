package com.example.room_tutorial;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "my_table")
public class MyModel {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "FirstName")
    private String fname;

    @ColumnInfo(name = "LastName")
    private String lname;

    @ColumnInfo(name = "Age")
    private int age;

    // constructor
    public MyModel(int id,String fname,String lname,int age)
    {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
    }


    // setters and getters
    public int getId(){
        return id;
    }
    public String getFname(){
        return fname;
    }
    public String getLname(){
        return lname;
    }
    public int getAge(){
        return age;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setFname(String fname){
        this.fname = fname;
    }
    public void setLname(String lname){
        this.lname = lname;
    }
    public void setAge(int age){
        this.age = age;
    }

}
