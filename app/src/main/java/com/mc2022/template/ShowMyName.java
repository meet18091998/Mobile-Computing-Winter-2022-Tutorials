package com.mc2022.template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowMyName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_my_name);

        Intent intent = getIntent();
        String name = intent.getStringExtra("my_name");

        ((TextView)findViewById(R.id.show_me_textview)).setText("Name from last activity is " + name);
    }
}