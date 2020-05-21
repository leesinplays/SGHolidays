package com.myapplicationdev.android.lp2_quiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<ToDo> {
    private ArrayList<ToDo> todos;
    private Context context;
    private TextView tvID, tvReminder, tvDate;

    public CustomAdapter(Context context, int resource, ArrayList<ToDo> objects){
        super(context, resource, objects);

        todos = objects;

        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row, parent, false);

        tvID = (TextView) rowView.findViewById(R.id.tvID);
        tvDate = (TextView) rowView.findViewById(R.id.tvDate);
        tvReminder = (TextView) rowView.findViewById(R.id.tvReminder);

        ToDo currentTodo = todos.get(position);

        tvID.setText(Integer.toString(currentTodo.getId()));
        tvReminder.setText(currentTodo.getData());
        tvDate.setText(currentTodo.getDate());

        return rowView;
    }
}
