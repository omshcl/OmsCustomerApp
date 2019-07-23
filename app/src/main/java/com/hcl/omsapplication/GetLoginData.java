package com.hcl.omsapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GetLoginData extends AsyncTask<String,Integer,String> {

    ResultsListener listener;
    private Context mContext;

    public GetLoginData(Context context) {
        mContext = context;
    }

    private String readStream(InputStream in) throws Exception {
        StringBuilder response = new StringBuilder();
        String line;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        while ((line = bufferedReader.readLine()) != null)
            response.append(line);
        return response.toString();
    }


    public void setOnResultsListener(ResultsListener listener) {
        this.listener = listener;
    }


    @Override
    protected String doInBackground(String[] strings) {
        String example = null;
        JSONObject loginData = null;
        final RequestQueue rq = Volley.newRequestQueue(mContext);
        Log.i("thing",strings[0] + " " + strings[1]);
        try {
            loginData = new JSONObject().put("username", strings[0]).put("password", strings[1]);
        } catch(JSONException e) {}

        Log.i("jsonobject"," " + loginData.toString());

        Log.e("background","Background network request started");
        JsonRequest<JSONObject> jsonRequest = null;
        try {
            ///URL url = new URL("http://10.0.2.2:8080/login");
            JSONObject data = null;
            jsonRequest = new JsonObjectRequest(Request.Method.POST, "http://10.0.2.2:8080/login", loginData,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("onSite", "response -> " + response.toString());
                            try {
                                listener.onResultsSucceeded(response.getBoolean("isValid"));
                            } catch (Exception e) {}

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("jsonRequest error", "  " + error);
                }
            });

        } catch (Exception e) {
            Log.e("error"," " + e  + " " + e.getMessage());
        }
        rq.add(jsonRequest);

        return example;
    }





}
