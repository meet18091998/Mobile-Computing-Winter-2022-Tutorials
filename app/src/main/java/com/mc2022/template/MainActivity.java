package com.mc2022.template;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String username;
    final static private String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*Log the Activity State*/
        Log.i(TAG,"I am onCreate()");

        /* Objects for views */
        Button buttonCool = (Button)findViewById(R.id.button_cool);
        Button buttonNice = (Button)findViewById(R.id.button_nice);
        Button buttonIntelligent = (Button)findViewById(R.id.button_intelligent);
        Button buttonBad = (Button)findViewById(R.id.button_bad);
        Button buttonRude = (Button)findViewById(R.id.button_rude);
        EditText textName = (EditText)findViewById(R.id.editText_username);

        if(savedInstanceState!=null){
            textName.setText(savedInstanceState.getString("username",this.username));
        }

        /*
            Listeners for the buttons to perform action on button click.
         */
        buttonCool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Display toast Message*/
                username = textName.getText().toString(); // get content written in text box.
                Log.d(TAG,username);
                if (username.equals("")){
                    // if username is not entered, show a toast that username is empty!
                    Toast.makeText(MainActivity.this, R.string.enter_name_first,Toast.LENGTH_SHORT).show();
                }else{
                    // if name is entered, display a message on toast.
                    String message = username+" is Cool";
                    Toast.makeText(MainActivity.this, message,Toast.LENGTH_SHORT).show();
                    /* Clear the content of TextBox*/
                    textName.setText("");
                }
            }
        });
        /*
            Listeners for the buttons to perform action on button click.
         */
        buttonNice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Display toast Message*/
                username = textName.getText().toString();
                Log.d(TAG,username);
                if (username.equals("")){
                    // if username is not entered, show a toast that username is empty!
                    Toast.makeText(MainActivity.this, R.string.enter_name_first,Toast.LENGTH_SHORT).show();
                }else{
                    // if name is entered, display a message on toast.
                    String message = username+" is Nice";
                    Toast.makeText(MainActivity.this, message,Toast.LENGTH_SHORT).show();
                    /* Clear the content of TextBox*/
                    textName.setText("");
                }
            }
        });
        /*
            Listeners for the buttons to perform action on button click.
         */
        buttonIntelligent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Display toast Message*/
                username = textName.getText().toString();
                Log.d(TAG,username);
                if (username.equals("")){
                    // if username is not entered, show a toast that username is empty!
                    Toast.makeText(MainActivity.this, R.string.enter_name_first,Toast.LENGTH_SHORT).show();
                }else{
                    // if name is entered, display a message on toast.
                    String message = username+" is Intelligent";
                    Toast.makeText(MainActivity.this, message,Toast.LENGTH_SHORT).show();
                    /* Clear the content of TextBox*/
                    textName.setText("");
                }
            }
        });
        /*
            Listeners for the buttons to perform action on button click.
         */
        buttonBad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Display toast Message*/
                username = textName.getText().toString();
                Log.d(TAG,username);
                if (username.equals("")){
                    // if username is not entered, show a toast that username is empty!
                    Toast.makeText(MainActivity.this, R.string.enter_name_first,Toast.LENGTH_SHORT).show();
                }else{
                    // if name is entered, display a message on toast.
                    String message = username+" is Bad";
                    Toast.makeText(MainActivity.this, message,Toast.LENGTH_SHORT).show();
                    /* Clear the content of TextBox*/
                    textName.setText("");
                }
            }
        });
        /*
            Listeners for the buttons to perform action on button click.
         */
        buttonRude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Display toast Message*/
                username = textName.getText().toString();
                Log.d(TAG,username);
                if (username.equals("")){
                    // if username is not entered, show a toast that username is empty!
                    Toast.makeText(MainActivity.this, R.string.enter_name_first,Toast.LENGTH_SHORT).show();
                }else{
                    // if name is entered, display a message on toast.
                    String message = username+" is Rude";
                    Toast.makeText(MainActivity.this, message,Toast.LENGTH_SHORT).show();
                    /* Clear the content of TextBox*/
                    textName.setText("");
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        /*Log the Activity State*/
        Log.i(TAG,"I am onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*Log the Activity State*/
        Log.i(TAG,"I am onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        /*Log the Activity State*/
        Log.i(TAG,"I am onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        /*Log the Activity State*/
        Log.i(TAG,"I am onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*Log the Activity State*/
        Log.i(TAG,"I am onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"I am onRestart()");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("username",this.username);
        Log.i(TAG,"I am onSaveInstanceState()");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG,"I am onRestoreInstanceState()");
    }

}