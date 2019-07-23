package com.hcl.omsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity implements ResultsListener {

    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void doLogin(View view) {
        Intent createOrderIntent = new Intent(this,CreateOrder.class);

        EditText username = (EditText)findViewById(R.id.username);
        EditText password = (EditText)findViewById(R.id.password);
        user = username.getText().toString();
        String pass = password.getText().toString();

        GetLoginData getLogin = new GetLoginData(this);
        getLogin.setOnResultsListener(this);

        getLogin.execute(user,pass);



    }

    public void handleError(Exception e) {
        String error = e.getMessage();
        Log.e("error",error);
    }

    @Override
    public void onResultsSucceeded(Boolean valid) {
        if(valid) {
            Intent createOrderIntent = new Intent(this,CreateOrder.class);
            createOrderIntent.putExtra("Username",user);
            startActivity(createOrderIntent);
        }
        Log.i("got it"," " + valid);

    }
}
