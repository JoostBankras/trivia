package com.example.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Spinner dropdown;
    Spinner dropdown2;
    Spinner dropdown3;
    String[] items;
    String[] items1;
    String[] items2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the spinner from the xml.
        dropdown = findViewById(R.id.spinner);
        dropdown2 = findViewById(R.id.spinner2);
        dropdown3 = findViewById(R.id.spinner3);
        //create a list of items for the spinner.
        items = new String[]{"Book", "Film", "Music", "Musicals & Theatres", "Television", "Video Games", "Board Games"};
        items1 = new String[]{"5", "10", "15", "20"};
        items2 = new String[]{"easy", "medium", "hard"};
        setAdapter(items, dropdown);
        setAdapter(items1, dropdown2);
        setAdapter(items2, dropdown3);


    }
    public void setAdapter(String[] items, Spinner dropdown){
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
    }
    public void onClick(View view){
        String text = dropdown.getSelectedItem().toString();
        String text1 = dropdown2.getSelectedItem().toString();
        String text2 = dropdown3.getSelectedItem().toString();
        Integer category = 0;
        for (int i=0; i < items.length; i++){
            if (text == items[i]){
                category = 10 + i;
            }
        }
        Intent intent = new Intent(MainActivity.this, questions.class);
        intent.putExtra("difficulty", text2 );
        intent.putExtra("category", category.toString() );
        intent.putExtra("amount", text1 );
        startActivity(intent);
    }
    public void onClick2(View view){
        Intent intent = new Intent(this, Highscores.class);
        startActivity(intent);
    }
}
