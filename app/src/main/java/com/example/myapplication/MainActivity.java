package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        final EditText editText10=(EditText)findViewById(R.id.editText10);
        final EditText editText11=(EditText)findViewById(R.id.editText11);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String url="http://10.0.2.2:8080/login";

               try {

                   JSONObject parameters = new JSONObject();
                   parameters.put("username", editText10.getText());
                   parameters.put("password", editText11.getText());
                   JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
                       @Override
                       public void onResponse(JSONObject response) {
                           try {
                               if(response.getString("isValid") == "true") {
                                   Intent i = new Intent(getApplicationContext(), CreateOrderActivity.class);
                                   i.putExtra("name", editText10.getText().toString());
                                   startActivity(i);
                               }
                               else
                               {
                                   Toast.makeText(getApplicationContext(),"Login Failed. Check Username and Password",Toast.LENGTH_LONG).show();
                               }
                           }
                           catch (JSONException e)
                           {
                               e.printStackTrace();
                           }

                       }
                   }, new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {
                           error.printStackTrace();


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
