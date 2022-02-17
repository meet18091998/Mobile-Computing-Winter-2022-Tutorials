package in.edu.iiitd.cosylab.tut2.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import in.edu.iiitd.cosylab.tut2.R;

public class FragmentOne extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn = view.findViewById(R.id.btnThree);

        /**
         *  btn.setOnClickListener(new View.OnClickListener() {
         *             @Override
         *             public void onClick(View view) {
         *                 FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
         *                 FragmentTransaction fragmentTransaction  = fragmentManager.beginTransaction();
         *                 fragmentTransaction.replace(R.id.frameLayoutOne, new FragmentTwo())
         *                         .setReorderingAllowed(true)
         *                         .commit();
         *             }
         *         });
         */

    }
}