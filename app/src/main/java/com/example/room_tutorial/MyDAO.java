package com.example.room_tutorial;


import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MyDAO {

    // list all
    @Query("Select * from my_table")
    List<MyModel> getList();

    // Insert
    @Insert
    void insert(MyModel myModel);

    // Delete using id
    @Query("DELETE from my_table where id = :id")
    void deleteUsingID(int id);

    // Delete using object
    @Delete
    void delete(MyModel myModel);

    // Update firstname
    @Query("Update my_table set Age = :age where FirstName = :fname")
    void update(String fname,int age);
}
