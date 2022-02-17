package in.edu.iiitd.cosylab.tut2.Broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import in.edu.iiitd.cosylab.tut2.R;

public class BroadCastActivity extends AppCompatActivity {
    BroadCast broadCast;
    TextView batteryLife;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast);
        batteryLife = (TextView) findViewById(R.id.textView);
        btn = (Button) findViewById(R.id.button);

        // BroadcastReceiver
        broadCast = new BroadCast(batteryLife);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(broadCast, intentFilter);

        // BroadCastSender
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("com.learning.sendAction");
                // setting the flag even receiving application is stop or killed
                intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadCast);
    }
}