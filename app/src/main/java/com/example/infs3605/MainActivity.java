package com.example.infs3605;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    // omg it works ok finally fml wtf that was so ugh
    
    
    // Implement Firebase logins + put in basic UI per BPMN - start connecting APIs

    private ImageView xBot;
    private ImageView xExplore;
    private ImageView xCam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xBot = findViewById(R.id.xBot);
        xExplore = findViewById(R.id.xExplore);
        xCam = findViewById(R.id.xCam);

        xBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchBot("");
            }
        });

        xExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchExplore("");
            }
        });

        xCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCam("");
            }
        });


    }

    private void launchBot(String message) {
        Intent intent = new Intent(this, BotActivity.class);
        startActivity(intent);

    }
    private void launchExplore(String message) {
        Intent intent = new Intent(this, ExploreActivity.class);
        startActivity(intent);

    }
    private void launchCam(String message) {
        Intent intent = new Intent(this, CamActivity.class);
        startActivity(intent);

    }
}
