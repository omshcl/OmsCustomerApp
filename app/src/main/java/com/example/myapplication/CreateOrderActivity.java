package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CreateOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        Button button3 = (Button) findViewById(R.id.button3);


        TextView textView3=(TextView)findViewById(R.id.textView3);
        Bundle bundle=getIntent().getExtras();
        String s=bundle.getString("name");
        textView3.setText("Welcome"+" "+s);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "http://10.0.2.2:8080/orders/new";

                try {
                    JSONObject parameters = new JSONObject();
                    JSONObject items = new JSONObject();
                    JSONObject quantity = new JSONObject();
                    JSONObject price = new JSONObject();

                    JSONArray itemlist = new JSONArray();
                    items.put("itemid", 5);
                    items.put("subtotal", 149);
                    itemlist.put(items);

                    JSONArray itemlist1 = new JSONArray();
                    quantity.put("itemid", 5);
                    quantity.put("quantity", 2);
                    itemlist1.put(quantity);

                    JSONArray itemlist2 = new JSONArray();
                    price.put("itemid", 5);
                    price.put("price", 2);
                    itemlist2.put(price);


                    parameters.put("address", "ad");
                    parameters.put("channel", "adf");
                    parameters.put("city", "sdf");
                    parameters.put("username", "dgfs");
                    parameters.put("date", "2019-07-23T18:12:48.422Z");
                    parameters.put("firstname", "dsgfdsg");
                    parameters.put("items", itemlist);
                    parameters.put("lastname", "");
                    parameters.put("ordertype", "dsg");
                    parameters.put("payment", "sdg");
                    parameters.put("quantity", itemlist1);
                    parameters.put("price", itemlist2);
                    parameters.put("shipnode", "daf");
                    parameters.put("state", "af");
                    parameters.put("total", 14353);
                    parameters.put("zip", "32252");
                    System.out.println(parameters);
                    JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Intent i=new Intent(getApplicationContext(),LocationActivity.class);
                            startActivity(i);

                            Toast.makeText(getApplicationContext(),"Order Placed",Toast.LENGTH_LONG).show();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();


                        }
                    });
                    Volley.newRequestQueue(CreateOrderActivity.this).add(jsonRequest);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

