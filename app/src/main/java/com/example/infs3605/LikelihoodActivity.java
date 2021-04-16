package com.example.infs3605;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class LikelihoodActivity extends AppCompatActivity {

    public static final String INTENT_MESSAGE = "intent message for likelihood";

    TextView tvLikelihood;
    ImageView ivLikelihood;
    static final String UNLIKELY = "UNLIKELY";
    static final String POSSIBLY = "POSSIBLY";
    static final String LIKELY = "LIKELY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likelihood);

        // read extra message from explicit intent
        Intent intent = getIntent();
        String likelihoodResult = intent.getStringExtra(INTENT_MESSAGE);

        tvLikelihood = findViewById(R.id.tvLikResult);
        ivLikelihood = findViewById(R.id.ivLikResult);

        switch (likelihoodResult) {
            case UNLIKELY:
                tvLikelihood.setText(likelihoodResult);
                ivLikelihood.setImageResource(R.drawable.result_unlikely);
                break;

            case POSSIBLY:
                tvLikelihood.setText(likelihoodResult);
                ivLikelihood.setImageResource(R.drawable.result_possibly);
                break;

            case LIKELY:
                tvLikelihood.setText(likelihoodResult);
                ivLikelihood.setImageResource(R.drawable.result_likely);
                break;
        }
    }

}