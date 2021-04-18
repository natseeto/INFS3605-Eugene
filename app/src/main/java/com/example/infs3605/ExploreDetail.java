package com.example.infs3605;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.net.URL;

public class ExploreDetail extends AppCompatActivity {

    ImageView mainImageView;
    TextView title, description, pubDate, creator;
    Button viewArticle;

    String yTitle, yCreator, yPubDate, yLink, yDesc, yThumb;
    String articleURL;
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

        getData();
        try {
            setData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        viewArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(articleURL));
                startActivity(intent);
            }
        });


    }

    private void getData() {
        if(getIntent().hasExtra("data1") && getIntent().hasExtra("data2")) {
            yTitle = getIntent().getStringExtra("data1");
            yCreator = getIntent().getStringExtra("data2");
            yPubDate = getIntent().getStringExtra("data3");
            yLink = getIntent().getStringExtra("data4");
            yDesc = getIntent().getStringExtra("data5");
            yThumb = getIntent().getStringExtra("data6");

        }else{
                Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() throws IOException {
        title.setText(yTitle);
        description.setText(yPubDate);
        creator.setText(yCreator);
        pubDate.setText(yDesc);
        articleURL = yLink;

        Glide.with(this).load(yThumb).into(mainImageView);


    }
}