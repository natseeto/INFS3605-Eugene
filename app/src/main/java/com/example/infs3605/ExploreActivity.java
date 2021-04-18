package com.example.infs3605;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.sun.jna.StringArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class ExploreActivity extends AppCompatActivity {

    private static final String TAG = "ExploreActivity";

    private static final String BASE_URL = "https://www.wired.com/";

    RecyclerView recyclerView;
    String s1[], s2[], s3[];
    int images[] = {R.drawable.one, R.drawable.two, R.drawable.three,R.drawable.four, R.drawable.fice,R.drawable.six,R.drawable.seven,R.drawable.password};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        setTitle("Article Feed");

        s3 = getResources().getStringArray(R.array.creator);
        s2 = getResources().getStringArray(R.array.pubDate);


        recyclerView = findViewById(R.id.recyclerView);

        ExploreAdapter exploreAdapter = new ExploreAdapter(this, s1, s2, s3, images);
        recyclerView.setAdapter(exploreAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        FeedAPI feedAPI = retrofit.create(FeedAPI.class);

        Call<RSS> call = feedAPI.getFeed();

        call.enqueue(new Callback<RSS>() {
            @Override
            public void onResponse(Call<RSS> call, Response<RSS> response) {
              // Log.d(TAG, "onResponse: feed: " + response.body().toString());
                Log.d(TAG, "onResponse: Server Response " + response.toString());

                List<Entry> entrys = response.body().getChannel().getEntrys();

                Log.d(TAG, "onResponse: title" +entrys.get(0).getTitle());
                Log.d(TAG, "onResponse: creator" +entrys.get(0).getCreator());
                Log.d(TAG, "onResponse: thumbnail"+entrys.get(0).getThumbnail().getUrl());


                ArrayList<Post> posts = new ArrayList<Post>();
                for (int j=0; j<entrys.size(); j++){
                    posts.add(new Post(
                            entrys.get(j).getTitle(),
                            entrys.get(j).getCreator(),
                            entrys.get(j).getPubDate(),
                            entrys.get(j).getThumbnail().getUrl(),
                            entrys.get(j).getLink(),
                            entrys.get(j).getDescription()
                    ));
                }

                for (int x = 0; x < entrys.size(); x++) {
                    s1[x] = entrys.get(x).getTitle();
                }

                for (int i =0; i< entrys.size(); i++){
                    Log.d(TAG, "onResponse: \n "+
                            "Title: " +entrys.get(i).getTitle() + "\n" +
                            "Creator: " +entrys.get(i).getCreator() + "\n" +
                            "pubDate: " +entrys.get(i).getPubDate() + "\n" +
                            "link: " +entrys.get(i).getLink() + "\n" +
                            "Desc: " +entrys.get(i).getDescription() + "\n" +
                            "Thumbnail: " +entrys.get(i).getThumbnail().getUrl() + "\n");
                }
            }

            @Override
            public void onFailure(Call<RSS> call, Throwable t) {
                    Log.e(TAG, "onFailure: Unable to retrieve RSS: " +t.getMessage());
                Toast.makeText(ExploreActivity.this, "An Error Occurred", Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void launchHome(String message) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}