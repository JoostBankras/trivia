package com.example.trivia;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuestionRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    String link;
    Context context;
    Callback activity1;

    public interface Callback {
        void gotQuestions(ArrayList<String> categories);
        void gotQuestionsError(String message);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d("debug1 :", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray array;
//        get the JSONArray's if possible
        try{
            ArrayList<String> list = new ArrayList<>();
            array = response.getJSONArray("results");
            Log.d("debuggen", array.toString());
            for (int i = 0; i < array.length(); i++ ){
                JSONObject object = array.getJSONObject(i);
                String question = object.getString("question");
                String correct_answer = object.getString("correct_answer");
                String line = question +"~"+ correct_answer;
                JSONArray wrong_answers = object.getJSONArray("incorrect_answers");
                for(int j =0; j<wrong_answers.length();j++){
                    line += "~" + wrong_answers.get(j).toString();
                }
                list.add(line);
            }
            activity1.gotQuestions(list);
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    public QuestionRequest(Context context1, String link1){
        link = link1;
        context = context1;
    }

    public void get_questions(Callback activity){
        activity1 = activity;
        System.out.println(link);
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(link,null, this, this);
        queue.add(jsonObjectRequest);
    }
}


