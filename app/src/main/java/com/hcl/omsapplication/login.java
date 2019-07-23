package com.hcl.omsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class login extends AppCompatActivity {
    private sampleGetApi sampleGetApi;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textViewResult = findViewById(R.id.text_view_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://4626254e.ngrok.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        sampleGetApi = retrofit.create(sampleGetApi.class);


    }

    public void login(View view) {
//        Intent createOrderIntent = new Intent(this, CreateOrder.class);
//        EditText username = (EditText) findViewById(R.id.username);
//        EditText password = (EditText) findViewById(R.id.password);
//        String user = username.getText().toString();
//        String pass = username.getText().toString();
//        createOrderIntent.putExtra("Username", user);
//        startActivity(createOrderIntent);
        loginPost();
    }

    private void loginPost() {

        LoginPost login = new LoginPost("pat_abh", "Admin!123");

        Call<JsonObject> call = sampleGetApi.loginPost(login);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                String s = String.valueOf(response.body());
                textViewResult.setText(s);

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
