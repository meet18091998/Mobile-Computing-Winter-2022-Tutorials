package in.edu.iiitd.cosylab.tut2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import in.edu.iiitd.cosylab.tut2.Broadcast.BroadCastActivity;
import in.edu.iiitd.cosylab.tut2.Fragment.Activity2;
import in.edu.iiitd.cosylab.tut2.Fragment.FragmentActivity;
import in.edu.iiitd.cosylab.tut2.service.ServiceActivity;

public class MainActivity extends AppCompatActivity {
    Button btn, serviceBtn, fragBtn, act2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button2);
        serviceBtn = (Button) findViewById(R.id.button3);
        fragBtn = (Button) findViewById(R.id.button6);
        act2 = (Button) findViewById(R.id.button7);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, BroadCastActivity.class);
                startActivity(i);
            }
        });

        serviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ServiceActivity.class);
                startActivity(i);
            }
        });

        fragBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, FragmentActivity.class);
                startActivity(i);
            }
        });

        act2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Activity2.class);
                startActivity(i);
            }
        });
    }
}