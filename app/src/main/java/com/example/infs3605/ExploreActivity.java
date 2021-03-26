package com.example.infs3605;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ExploreActivity extends AppCompatActivity {

    //connect in RSS feed per BPMN
    //recyclerview

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
    }

    private void launchHome(String message) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}