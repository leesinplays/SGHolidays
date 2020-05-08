package com.example.p03_classjournal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ModuleAdapter extends ArrayAdapter<ModuleCode> {
    private ArrayList<ModuleCode> modules;
    private Context context;
    private TextView tvCode;
    private TextView tvName;

    public ModuleAdapter(Context context, int resource, ArrayList<ModuleCode> objects) {
        super(context, resource, objects);
        // Store the dailyGrade that is passed to this adapter
        modules = objects;
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
        View rowView = inflater.inflate(R.layout.modulerow, parent, false);

        // Get the TextView object
        tvCode = (TextView) rowView.findViewById(R.id.tvCode);
        tvName = (TextView) rowView.findViewById(R.id.tvName);

        ModuleCode currentModule = modules.get(position);

        tvCode.setText(currentModule.getCode());
        tvName.setText(currentModule.getName());
        // Return the nicely done up View to the ListView
        return rowView;
    }
}
