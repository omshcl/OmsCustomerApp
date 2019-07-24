package com.hcl.omsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class login extends AppCompatActivity {
    private apiCalls apiCalls;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textViewResult = findViewById(R.id.text_view_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://d0faf90d.ngrok.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiCalls = retrofit.create(apiCalls.class);
    }

    public void login(View view) {
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        String user = username.getText().toString();
        String pass = password.getText().toString();
        loginPost(user, pass);
    }

    private void loginPost(String user, String pass) {
        final LoginPost login = new LoginPost(user, pass);
        Call<LoginStatus> call = apiCalls.loginPost(login);

        call.enqueue(new Callback<LoginStatus>() {
            @Override
            public void onResponse(Call<LoginStatus> call, Response<LoginStatus> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                LoginStatus status = response.body();

                if (status.isValid) {
                    Intent createOrderIntent = new Intent(getApplicationContext(), CreateOrder.class);
                    textViewResult.setText("Success");
                    createOrderIntent.putExtra("Username", "user");
                    startActivity(createOrderIntent);
                } else
                    textViewResult.setText("Failure");

            }

            @Override
            public void onFailure(Call<LoginStatus> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
