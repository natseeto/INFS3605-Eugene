package com.example.infs3605;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ExploreDetail extends AppCompatActivity {

    ImageView mainImageView;
    TextView title, description, pubDate, creator;
    Button viewArticle;

    String data1, data2, data3, data4;
    int myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_detail);

        mainImageView = findViewById(R.id.mImg);
        title = findViewById(R.id.mTitle);
        description = findViewById(R.id.mDesc);
        pubDate = findViewById(R.id.mPubDate);
        creator = findViewById(R.id.mCreator);
        viewArticle = findViewById(R.id.viewArticle);

        viewArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.wired.com/2017/03/phishing-scams-fool-even-tech-nerds-heres-avoid/"));
                startActivity(intent);
            }
        });

        getData();
        setData();
    }

    private void getData() {
        if(getIntent().hasExtra("myImage") && getIntent().hasExtra("data1") && getIntent().hasExtra("data2")) {
            data1 = getIntent().getStringExtra("data1");
            data2 = getIntent().getStringExtra("data2");
            myImage = getIntent().getIntExtra("myImage", 1);

        }else{
                Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {
        title.setText(data1);
        description.setText(data2);
        mainImageView.setImageResource(myImage);

    }
}