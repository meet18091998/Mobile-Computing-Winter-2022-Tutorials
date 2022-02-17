package in.edu.iiitd.cosylab.broadcastrec;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    private TextView textView;
    public MyReceiver(TextView textView) {
        this.textView = textView;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        textView.setText("BroadCast Receive");
        Log.i("LINE_14", "My BroaddCast receiver");
        Toast.makeText(context,"My BroaddCast receiver ", Toast.LENGTH_LONG).show();
    }
}