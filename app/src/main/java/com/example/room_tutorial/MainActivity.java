package com.example.room_tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView tv;
        tv = (TextView) findViewById(R.id.dataentries);

        // create instance of Database
        MyDatabase mydb = MyDatabase.getInstance(this);


        // Insert into Database
        MyModel myModel1 = new MyModel(1, "ABC","XYZ",23);
        MyModel myModel2 = new MyModel(2,"DEF","UVW",24);
        MyModel myModel3 = new MyModel(3,"GHI","PQR",22);
        MyModel myModel4 = new MyModel(4,"ABC","PQR",27);
        MyModel myModel5 = new MyModel(5,"DEF","XYZ",25);

        mydb.mydao().insert(myModel1);
        mydb.mydao().insert(myModel2);
        mydb.mydao().insert(myModel3);
        mydb.mydao().insert(myModel4);
        mydb.mydao().insert(myModel5);


        // Delete from Database using id
        mydb.mydao().deleteUsingID(3);

        // Delete from Database using object
        mydb.mydao().delete(myModel2);

        // Update database
        mydb.mydao().update("DEF",24);

        // Get List
        List<MyModel> myEntries = mydb.mydao().getList();

        String output = "";
        for(MyModel m : myEntries)
        {
            output += Integer.toString(m.getId()) + " " + m.getFname() + " " + m.getLname() + " " + Integer.toString(m.getAge()) + "\n";

         }

        Log.i("My Output : ",output);
        //tv.setText("Database Created Succcessfully !!! ");




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}