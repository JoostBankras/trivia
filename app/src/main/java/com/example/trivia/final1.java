package com.example.trivia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class final1 extends AppCompatActivity implements Response.Listener, Response.ErrorListener{

    String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_page);
//        get highscore from intent and set in view
        Intent intent = getIntent();
        score = intent.getSerializableExtra("score").toString();
        TextView textView = findViewById(R.id.score);
        textView.setText(score);
    }

    public void onClick1(View view){
//        make request for data from database
        TextView textView = findViewById(R.id.editText);
        String text = textView.getText().toString();
        String url = "https://ide50-joostbankras.legacy.cs50.io:8080/list";
        RequestQueue queue = Volley.newRequestQueue(this);
        TextView textView1 = findViewById(R.id.score);
        String score = textView1.getText().toString();
        PostRequest request = new PostRequest(Request.Method.POST, url, this, this, text, score );
        queue.add(request);
        startActivity(new Intent(this, MainActivity.class));

    }

//    on response
    @Override
    public void onErrorResponse(VolleyError error) {
    }

//    on error
    @Override
    public void onResponse(Object response){
    }
}
