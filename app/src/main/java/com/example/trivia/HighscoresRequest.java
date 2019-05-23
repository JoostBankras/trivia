package com.example.trivia;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HighscoresRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

    //    variables
    Context context;

    Callback activity1;
    ArrayList<String> list = new ArrayList<String>();
    Integer joe1;
    String url;
    //    interface for functions
    public interface Callback {
        void gotHighscores(String name, String Highscore);
        void gotCategoriesError(String message);
    }

    //    if JSONObject isn't received properly
    @Override
    public void onErrorResponse(VolleyError error) {
        activity1.gotCategoriesError("go on");
    }

    //    if JSONObject is received properly
    @Override
    public void onResponse(JSONObject response) {

//        get the JSONArray's if possible
        try{
            String object = response.getString("name");
            String object1 = response.getString("score");
            activity1.gotHighscores(object, object1);
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    //    constructor for CategoriesRequest
    public HighscoresRequest(Context context1, Integer joe){
        context = context1;
        joe1 = joe;
        url = "https://ide50-joostbankras.legacy.cs50.io:8080/list/" + joe1;
    }

    //    callable function for Categories
    public void getHighscores(Callback activity){
        activity1 = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,null, this, this);
        queue.add(jsonObjectRequest);

    }
}
