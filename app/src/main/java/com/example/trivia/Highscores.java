package com.example.trivia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Highscores extends AppCompatActivity implements HighscoresRequest.Callback {

    HighscoresRequest data;
    Integer i = 1;
    ArrayList<String> string1 = new ArrayList<>();

//    got highscore
    @Override
    public void gotHighscores(String name, String Highscore) {
//        add all data to the string
        string1.add(name + "~" + Highscore);

//        go to next part of data list
        i += 1;
        data = new HighscoresRequest(this, i);
        data.getHighscores(this);
    }

//    got error
    @Override
    public void gotCategoriesError(String message) {
//        there is no more data so load view
        ListView listview = findViewById(R.id.ListView);
        listview.setAdapter(new ListItemAdapter(this, 0, string1));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        //        on create make a request for data
        data = new HighscoresRequest(this, i);
        data.getHighscores(this);
        string1.add("NAME" + "~" + "HIGHSCORE");
    }
}
