package com.hcl.omsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CreateOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String username = intent.getStringExtra("Username");
        TextView welcome = findViewById(R.id.welcome);
        welcome.setText("Welcome: " + username);
    }


}
