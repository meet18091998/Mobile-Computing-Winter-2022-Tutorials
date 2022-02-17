package in.edu.iiitd.cosylab.tut2.service;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import in.edu.iiitd.cosylab.tut2.R;

public class ServiceActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.O)

    TextView textView;
    Button btnOne, btnTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        btnOne = (Button) findViewById(R.id.button4);
        btnTwo = (Button) findViewById(R.id.button5);

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backgroundService = new Intent(ServiceActivity.this, BackgroundService.class);
                startService(backgroundService);
            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Intent foregroundService = new Intent(ServiceActivity.this, ForegroundService.class);
                startForegroundService(foregroundService);
            }
        });
    }
}