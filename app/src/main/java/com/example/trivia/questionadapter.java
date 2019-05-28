package com.example.trivia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class questionadapter extends ArrayAdapter<String> {

    public ArrayList<String> ListItems;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        if there is no view, make a view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_questions, parent, false);
        }
        ArrayList <RadioButton> buttons = new ArrayList<>();

//        set all the buttons with answers
        RadioButton btn1 = convertView.findViewById(R.id.radioButton);
        RadioButton btn2 = convertView.findViewById(R.id.radioButton2);
        RadioButton btn3 = convertView.findViewById(R.id.radioButton3);
        RadioButton btn4 = convertView.findViewById(R.id.radioButton4);

        buttons.add(btn1); buttons.add(btn2); buttons.add(btn3); buttons.add(btn4);

        String[] text = ListItems.get(position).split("~");
        TextView textview = convertView.findViewById(R.id.question);
        textview.setText(text[0]);
        Random random = new Random();
        for (int i=2;i<buttons.size();i++){
            RadioButton btn = buttons.get(random.nextInt(buttons.size()));
            buttons.remove(btn);
            btn.setText(text[i]);
        }

        return convertView;
    }

//    adapter for questions view
    public questionadapter(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        ListItems = objects;
    }
}
