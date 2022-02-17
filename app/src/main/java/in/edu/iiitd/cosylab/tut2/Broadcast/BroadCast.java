package in.edu.iiitd.cosylab.tut2.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

public class BroadCast extends BroadcastReceiver {
    private static final String AIRPLANE = "android.intent.action.ACTION_AIRPLANE_MODE_CHANGED";
    private static final String BATTERY_STATUS = "android.intent.action.ACTION_BATTERY_OKAY";
    private TextView textView;
    public BroadCast(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action==AIRPLANE) {
            Log.i("LINE_21", "I am here");
            textView.setText("Airplane mode");
        }
        else if(action==BATTERY_STATUS) {
            int per = intent.getIntExtra("level", 0);
            textView.setText("Battery Life "+per+" %");
        }
    }
}
