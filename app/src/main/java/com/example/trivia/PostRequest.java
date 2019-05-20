package com.example.trivia;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PostRequest extends StringRequest  {

    String name1;
    String score1;

    // Constructor
    public PostRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener, String name, String score) {
        super(method, url, listener, errorListener);
        name1 = name;
        score1 = score;
    }

    // Method to supply parameters to the request
    @Override
    protected Map<String, String> getParams() {

        Map<String, String> params = new HashMap<>();
        params.put("name", name1);
        params.put("score", score1);
        return params;
    }
}