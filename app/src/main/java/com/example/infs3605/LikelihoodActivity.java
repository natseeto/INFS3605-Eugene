package com.example.infs3605;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class LikelihoodActivity extends AppCompatActivity {

    public static final String INTENT_MESSAGE = "intent message for likelihood";

    TextView tvLikelihood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likelihood);

        // read extra message from explicit intent
        Intent intent = getIntent();
        String result = intent.getStringExtra(INTENT_MESSAGE);

        tvLikelihood = findViewById(R.id.tvLikResult);
        tvLikelihood.setText(result);
    }
}