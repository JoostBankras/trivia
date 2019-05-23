package com.example.trivia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListItemAdapter extends ArrayAdapter<String> {
//    items for listview
    public ArrayList<String> ListItems;

//    puts everything in the adapterview
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        if there is no view, make a view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.highscores, parent, false);
        }
        String string = ListItems.get(position);
        String name = string.split("~")[0];
        String score = string.split("~")[1];
//        get variables for list item
        TextView text = convertView.findViewById(R.id.textView5);
        TextView text1 = convertView.findViewById(R.id.textView8);
        text.setText(name);
        text1.setText(score);
        return convertView;
    }

//    call this function for adapting everything for the list
    public ListItemAdapter(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        ListItems = objects;
    }
}

