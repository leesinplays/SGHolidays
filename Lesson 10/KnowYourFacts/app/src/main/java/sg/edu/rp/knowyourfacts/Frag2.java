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

public class Frag2 extends Fragment {

    TextView frag2;
    Button btnChangeColor;

    public Frag2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_frag2, container,false);
        frag2 = view.findViewById(R.id.frag2);
        frag2.setText("Of all the words in the English language, the word 'set' has the most definitions!\n\n" +
                "What is called a 'French kiss' in the English speaking world is known as an 'English kiss' in France.");
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
