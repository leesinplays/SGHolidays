package sg.edu.rp.knowyourfacts;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class Frag3 extends Fragment {

    TextView frag3;
    Button btnChangeColor;

    public Frag3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_frag3, container,false);
        frag3 = view.findViewById(R.id.frag3);
        frag3.setText("The word 'queue' is the only word in the English language that is still pronounced the same way when the last four letters are removed.\n\n" +
                "Beetles taste like apples, wasps like pine nuts, and worms  like fried bacon.");
        btnChangeColor = view.findViewById(R.id.btnChangeColor);
        btnChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rnd = new Random();
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                view.setBackgroundColor(color);
            }
        });
        return view;
    }
}
