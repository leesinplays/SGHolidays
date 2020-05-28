package com.example.taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Task> {
    private ArrayList<Task> tasks;
    private Context context;
    private TextView tvName, tvDesc;

    public TaskAdapter(Context context, int resource, ArrayList<Task> objects){
        super(context, resource, objects);

        tasks = objects;

        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row, parent, false);

        tvName = (TextView) rowView.findViewById(R.id.tvName);
        tvDesc = (TextView) rowView.findViewById(R.id.tvDesc);

        Task currentTask = tasks.get(position);

        tvName.setText(currentTask.getName());
        tvDesc.setText(currentTask.getDesc());

        return rowView;
    }
}
