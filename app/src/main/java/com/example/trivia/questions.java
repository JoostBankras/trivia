package com.example.trivia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.XMLFormatter;

public class questions extends AppCompatActivity implements QuestionRequest.Callback{

    String category;
    String difficulty;
    Integer amount;
    String link;
    Integer count = 0;
    ArrayList<String> questions;
    String correct;
    ArrayList <RadioButton> buttons;
    Integer score = 0;
    RadioButton btn1;
    RadioButton btn2;
    RadioButton btn3;
    RadioButton btn4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

//        get everything from intent
        Intent intent = getIntent();
        difficulty = intent.getSerializableExtra("difficulty").toString();
        category = intent.getSerializableExtra("category").toString();
        String amount1 = intent.getSerializableExtra("amount").toString();
        amount = Integer.parseInt(amount1);

//        make the link for API
        link = "https://opentdb.com/api.php?amount=" + amount + "&category=" + category + "&type=multiple&difficulty=" + difficulty;
        QuestionRequest data = new QuestionRequest(this, link);
        data.get_questions(this);

//        get all the saved values
        if (load_score() != null){
            score = load_score();
        }
        if (load_count() != null){
            count = load_count();
        }

    }

    @Override
    public void gotQuestions(ArrayList<String> questions1) {
//        when questions come in, set them in view
        questions = questions1;
        set_questions(questions.get(count));
    }

    public void save(){
//        save all the values
        SharedPreferences sharedPreferences=getSharedPreferences("score",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("score",score);
        SharedPreferences sharedPreferences1=getSharedPreferences("count",MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
        editor1.putInt("count",count);
        editor1.commit();
        editor.commit();
    }

//    load all the saved score
    public Integer load_score(){
        SharedPreferences sharedPreferences=getSharedPreferences("score",MODE_PRIVATE);
        Integer score2 =sharedPreferences.getInt("score",0);
        return score2;
    }

//    load the amount of answered questions
    public Integer load_count(){
        SharedPreferences sharedPreferences=getSharedPreferences("count",MODE_PRIVATE);
        Integer count1 =sharedPreferences.getInt("count",0);
        return count1;
    }

//    check if answer is correct, add to score
    public void onClick(View view){
        count += 1;
        save();

//        all check functions
        if(btn1.isChecked()){
            if (btn1.getContentDescription() == correct){
                score += 1;
            }
        }
        if(btn2.isChecked()){
            if (btn2.getContentDescription() == correct){
                score += 1;
            }
        }
        if(btn3.isChecked()){
            if (btn3.getContentDescription()== correct){
                score += 1;
            }
        }
        if(btn4.isChecked()){
            if (btn4.getContentDescription() == correct){
                score += 1;
            }

        }

//        continue firing questions
        if (count < questions.size()){
            set_questions(questions.get(count));
        }

//        else start final intent
        else{
            Intent intent = new Intent(this, final1.class);
            intent.putExtra("score", score.toString());
            count = 0;
            score = 0;
            save();
            startActivity(intent);
        }


    }


    @Override
    public void gotQuestionsError(String message) {

    }

//    set the question view with all the possible answers
    public void set_questions(String question){
        String[] text = question.split("~");
        buttons = new ArrayList<>();

        btn1 = findViewById(R.id.radioButton);
        btn2 = findViewById(R.id.radioButton2);
        btn3 = findViewById(R.id.radioButton3);
        btn4 = findViewById(R.id.radioButton4);

        buttons.add(btn1); buttons.add(btn2); buttons.add(btn3); buttons.add(btn4);

        TextView textview = findViewById(R.id.question);
        textview.setText(Html.fromHtml(text[0]));
        Random random = new Random();
        correct = text[1];

//        set all buttons
        for (int i=1;i<5;i++){
            RadioButton btn = buttons.get(random.nextInt(buttons.size()));
            buttons.remove(btn);
            btn.setText(Html.fromHtml(text[i]).toString());
            btn.setContentDescription(text[i]);
        }
    }
}
