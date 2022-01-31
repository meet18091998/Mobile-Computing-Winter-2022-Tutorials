package com.mc2022.template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPref;
    int openCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = getSharedPreferences("my-application", MODE_PRIVATE);

        ((Button)findViewById(R.id.show_me)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String name = ((EditText)findViewById(R.id.my_name)).getText().toString();

               Intent intent = new Intent(getApplicationContext(), ShowMyName.class);
               intent.putExtra("my_name", name);
               startActivity(intent);
            }
        });

        ((Button)findViewById(R.id.show_another_name)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ((EditText)findViewById(R.id.my_name)).getText().toString();

                Intent intent = new Intent(getApplicationContext(), ShowMyAnotherName.class);
                intent.putExtra("my_name", name);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        openCount = sharedPref.getInt("open-count-new", 0);
        openCount += 1;
        TextView tv = (TextView) findViewById(R.id.openCount);

        tv.setText("Opened " + openCount + " times!");
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences.Editor ed = sharedPref.edit();
        ed.putInt("open-count-new", openCount);
        ed.apply();
    }
}