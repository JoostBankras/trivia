package com.example.trivia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Highscores extends AppCompatActivity implements HighscoresRequest.Callback {

    HighscoresRequest data;
    Integer i = 1;
    ArrayList<String> string1 = new ArrayList<>();

    @Override
    public void gotHighscores(String name, String Highscore) {
        string1.add(name + "~" + Highscore);
        i += 1;
        data = new HighscoresRequest(this, i);
        data.getHighscores(this);
    }

    @Override
    public void gotCategoriesError(String message) {
        ListView listview = findViewById(R.id.ListView);
        System.out.println("joe?");
        listview.setAdapter(new ListItemAdapter(this, 0, string1));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);
        data = new HighscoresRequest(this, i);
        data.getHighscores(this);
        string1.add("NAME" + "~" + "HIGHSCORE");
    }
}
