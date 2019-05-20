package com.example.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    Integer fault =0;
    RadioButton btn1;
    RadioButton btn2;
    RadioButton btn3;
    RadioButton btn4;


    public Integer getScore() {
        return score;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        Intent intent = getIntent();
        difficulty = intent.getSerializableExtra("difficulty").toString();
        category = intent.getSerializableExtra("category").toString();
        String amount1 = intent.getSerializableExtra("amount").toString();
        amount = Integer.parseInt(amount1);
        link = "https://opentdb.com/api.php?amount=" + amount + "&category=" + category + "&type=multiple&difficulty=" + difficulty;
        QuestionRequest data = new QuestionRequest(this, link);
        data.get_questions(this);
    }

    @Override
    public void gotQuestions(ArrayList<String> questions1) {
        questions = questions1;
        System.out.println(questions);
        set_questions(questions.get(count));
    }

    public void onClick(View view){
        count += 1;
        System.out.println("correct1 : " + correct);
        if(btn1.isChecked()){
            if (btn1.getText() == correct){
                score += 1;
            }
            else{
                fault += 1;
            }
        }
        if(btn2.isChecked()){
            if (btn2.getText() == correct){
                score += 1;
            }
            else{
                fault += 1;
            }
        }
        if(btn3.isChecked()){
            if (btn3.getText() == correct){
                score += 1;
            }
            else{
                fault += 1;
            }
        }
        if(btn4.isChecked()){
            if (btn4.getText() == correct){
                score += 1;
            }
            else{
                fault += 1;
            }
        }
        if (count < questions.size()){
            System.out.println("score" + score + fault);

            set_questions(questions.get(count));
        }
        else{
            Intent intent = new Intent(this, final1.class);
            intent.putExtra("score", score.toString());
            intent.putExtra("fault", fault.toString());
            startActivity(intent);
        }


    }


    @Override
    public void gotQuestionsError(String message) {

    }

    public void set_questions(String question){
        String[] text = question.split("~");
        buttons = new ArrayList<>();

        btn1 = findViewById(R.id.radioButton);
        btn2 = findViewById(R.id.radioButton2);
        btn3 = findViewById(R.id.radioButton3);
        btn4 = findViewById(R.id.radioButton4);

        buttons.add(btn1); buttons.add(btn2); buttons.add(btn3); buttons.add(btn4);

        TextView textview = findViewById(R.id.question);
        textview.setText(text[0]);
        Random random = new Random();
        correct = text[1];
        System.out.println("correct :" + correct);
        for (int i=1;i<5;i++){
            RadioButton btn = buttons.get(random.nextInt(buttons.size()));
            buttons.remove(btn);
            btn.setText(text[i]);
        }
    }
}
