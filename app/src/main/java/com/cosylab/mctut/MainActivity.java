package com.cosylab.mctut;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;

import android.content.ContentValues;
import android.content.CursorLoader;

import android.database.Cursor;

import android.view.Menu;
import android.view.View;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClickAddName(View view) {
        // Add a new student record
        ContentValues values = new ContentValues();
        values.put(StudentsProvider.NAME,
                ((EditText)findViewById(R.id.editText2)).getText().toString());

        values.put(StudentsProvider.GRADE,
                ((EditText)findViewById(R.id.editText3)).getText().toString());

        Uri uri = getContentResolver().insert(
                StudentsProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
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
                strBuild.append("\n"+cursor.getString(cursor.getColumnIndex(StudentsProvider.ID))+"-"+ cursor.getString(cursor.getColumnIndex(StudentsProvider.NAME))+"-"+ cursor.getString(cursor.getColumnIndex(StudentsProvider.GRADE)));
                cursor.moveToNext();
            }
            resultView.setText(strBuild);
        }
        else {
            resultView.setText("No Records Found");
        }
    }
}