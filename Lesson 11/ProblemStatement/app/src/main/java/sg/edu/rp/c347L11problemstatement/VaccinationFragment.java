package sg.edu.rp.c347L11problemstatement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class VaccinationFragment extends Fragment implements VaccinationDialog.OnInputSelected{

    private static final String TAG = "VaccinationFragment";
    Button btnEdit;
    TextView tvVaccination;

    public VaccinationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vaccination, container, false);
        btnEdit = view.findViewById(R.id.btnEditVaccination);
        tvVaccination = view.findViewById(R.id.tvVaccination);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: opening dialog");

                VaccinationDialog dialog = new VaccinationDialog();
                dialog.setTargetFragment(VaccinationFragment.this, 1);
                dialog.show(getFragmentManager(), "VaccinationDialog");
            }
        });


        return view;
    }

    @Override
    public void sendInput(String input) {
        Log.d(TAG, "sendInput: found incoming input: " + input);
        tvVaccination.setText(input);
    }
}
