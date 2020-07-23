package sg.edu.rp.knowyourfacts;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Random;

public class Frag1 extends Fragment {

    TextView frag1;
    Button btnChangeColor;

    public Frag1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_frag1, container,false);
        frag1 = view.findViewById(R.id.frag1);
        frag1.setText("'Almost' is the longest word in the English language with all the letters in alphabetical order.\n\n"+
                "'Rhythm' is the longest English word without a vowel.");
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
