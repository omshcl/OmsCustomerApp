package com.hcl.omsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        Intent createOrderIntent = new Intent(this,CreateOrder.class);
        EditText username = (EditText)findViewById(R.id.username);
        EditText password = (EditText)findViewById(R.id.password);
        String user = username.getText().toString();
        String pass = username.getText().toString();
        createOrderIntent.putExtra("Username",user);
        startActivity(createOrderIntent);
    }
}
