package com.cosylab.mctutresolver;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.net.Uri;
import android.database.Cursor;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final String ID = "_id";
    static final String NAME = "name";
    static final String GRADE = "grade";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @SuppressLint("Range")
    public void onClickRetrieveStudents(View view) {

        TextView resultView= (TextView) findViewById(R.id.res);

        // Retrieve student records
        String URL = "content://com.cosylab.mctut.StudentsProvider";

        Uri students = Uri.parse(URL);
        Cursor cursor =getContentResolver().query(students, null, null, null, null);

        if(cursor.moveToFirst()) {
            StringBuilder strBuild=new StringBuilder();
            while (!cursor.isAfterLast()) {
                strBuild.append("\n"+cursor.getString(cursor.getColumnIndex(ID))+"-"+ cursor.getString(cursor.getColumnIndex(NAME))+"-"+ cursor.getString(cursor.getColumnIndex(GRADE)));
                cursor.moveToNext();
            }
            resultView.setText(strBuild);
        }
        else {
            resultView.setText("No Records Found");
        }

    }
}