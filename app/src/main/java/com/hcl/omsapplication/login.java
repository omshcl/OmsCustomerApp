package com.hcl.omsapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity implements ResultsListener {

    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannel();
        setContentView(R.layout.activity_login);
    }



    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(getString(R.string.channel_id), name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
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
        } else {
            Toast error = Toast.makeText(this,"Login Failed",Toast.LENGTH_LONG);
            error.setGravity(Gravity.CENTER,0,0);
            error.show();
        }
        Log.i("got it"," " + valid);

    }
}
