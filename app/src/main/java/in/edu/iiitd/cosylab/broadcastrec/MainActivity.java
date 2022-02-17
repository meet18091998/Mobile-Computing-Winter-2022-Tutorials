package in.edu.iiitd.cosylab.broadcastrec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView) findViewById(R.id.txt);
        IntentFilter intentFilter = new IntentFilter("com.sendAction");
        MyReceiver myReceiver = new MyReceiver(txt);
        registerReceiver(myReceiver, intentFilter);
    }
}