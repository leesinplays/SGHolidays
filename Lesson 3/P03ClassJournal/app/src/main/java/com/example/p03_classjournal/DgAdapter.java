package com.example.p03_classjournal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DgAdapter extends ArrayAdapter<DailyCA> {
    private ArrayList<DailyCA> dgs;
    private Context context;
    private TextView tvDg;
    private TextView tvWeek;
    private ImageView ivDg;

    public DgAdapter(Context context, int resource, ArrayList<DailyCA> objects) {
        super(context, resource, objects);
        // Store the dailyGrade that is passed to this adapter
        dgs = objects;
        // Store Context object as we would need to use it later
        this.context = context;
    }

    // getView() is the method ListView will call to get the
    //  View object every time ListView needs a row
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // The usual way to get the LayoutInflater object to
        //  "inflate" the XML file into a View object
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // "Inflate" the row.xml as the layout for the View object
        View rowView = inflater.inflate(R.layout.dgrow, parent, false);

        // Get the TextView object
        tvDg = (TextView) rowView.findViewById(R.id.tvDg);
        tvWeek = (TextView) rowView.findViewById(R.id.tvWeek);
        // Get the ImageView object
        ivDg = (ImageView) rowView.findViewById(R.id.ivDg);

        DailyCA currentDg = dgs.get(position);

        tvDg.setText(currentDg.getDgGrade());
        tvWeek.setText(currentDg.getWeek());
        ivDg.setImageResource(R.drawable.dg);
        // Return the nicely done up View to the ListView
        return rowView;
    }
}
