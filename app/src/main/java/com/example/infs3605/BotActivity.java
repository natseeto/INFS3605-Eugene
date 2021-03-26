package com.example.infs3605;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class BotActivity extends AppCompatActivity {

    //Justin to put bot stuff here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot);
    }

    private void launchHome(String message) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}