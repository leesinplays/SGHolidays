package sg.edu.rp.c347L11problemstatement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class BioFragment extends Fragment implements BioDialog.OnInputSelected {

    private static final String TAG = "BioFragment";
    Button btnEdit;
    TextView tvBio;

    public BioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bio, container, false);
        btnEdit = view.findViewById(R.id.btnEditBio);
        tvBio = view.findViewById(R.id.tvBio);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: opening dialog");

                BioDialog dialog = new BioDialog();
                dialog.setTargetFragment(BioFragment.this, 1);
                dialog.show(getFragmentManager(), "BioDialog");
            }
        });


        return view;
    }

    @Override
    public void sendInput(String input) {
        Log.d(TAG, "sendInput: found incoming input: " + input);
        tvBio.setText(input);
    }
}
