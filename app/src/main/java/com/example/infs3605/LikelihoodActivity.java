package com.example.infs3605;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LikelihoodActivity extends AppCompatActivity {

    public static final String INTENT_MESSAGE = "intent message for likelihood";

    TextView tvLikelihood;
    ImageView ivLikelihood;
    ConstraintLayout cardLayout;
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
        cardLayout = findViewById(R.id.cardLayout);


        switch (likelihoodResult) {
            case UNLIKELY:
                tvLikelihood.setText("This email is unlikely to be a scam\nHowever, I suggest you to read more about phishing");
                ivLikelihood.setImageResource(R.drawable.result_unlikely);
                break;

            case POSSIBLY:
                tvLikelihood.setText("Be careful.\nThis email can possibly be a scam");
                ivLikelihood.setImageResource(R.drawable.result_possibly);
                break;

            case LIKELY:
                tvLikelihood.setText("Be careful!\nThis email is likely to be a scam!");
                ivLikelihood.setImageResource(R.drawable.result_likely);
                break;
        }

        cardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.wired.com/2017/03/phishing-scams-fool-even-tech-nerds-heres-avoid/"));
                startActivity(intent);
            }
        });
    }

}