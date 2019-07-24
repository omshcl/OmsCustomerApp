package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button2=(Button)findViewById(R.id.button2);
        final TextView textView=(TextView)findViewById(R.id.textView);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="http://10.0.2.2:8080/login";

               try {

                   JSONObject parameters = new JSONObject();
                   parameters.put("username", "admin");
                   parameters.put("password", "Admin!123");
                   JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
                       @Override
                       public void onResponse(JSONObject response) {
                           textView.setText(response.toString());
                           Intent i=new Intent(getApplicationContext(),CreateOrderActivity.class);
                           startActivity(i);

                       }
                   }, new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {
                           error.printStackTrace();
                           textView.setText(error.toString());

                       }
                   });
                   Volley.newRequestQueue(MainActivity.this).add(jsonRequest);
               }
               catch (JSONException e)
               {
                   e.printStackTrace();
               }
        }});
    }
}
