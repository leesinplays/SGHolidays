package sg.edu.rp.demoswiper;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Frag1 extends Fragment {

    TextView frag1;

    public Frag1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag1, container,false);
        frag1 = view.findViewById(R.id.frag1);
        frag1.setText("This is the first fragment");
        return view;
    }
}
