package in.edu.iiitd.cosylab.tut2.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import in.edu.iiitd.cosylab.tut2.R;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        /**
         *
         FragmentManager fragmentManager = getSupportFragmentManager();
         FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();
         fragmentTransaction.replace(R.id.frameLayoutOne, new FragmentOne())
         .setReorderingAllowed(true)
         .commit();
         * **/
    }
}