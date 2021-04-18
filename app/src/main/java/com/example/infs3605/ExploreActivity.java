package com.example.infs3605;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
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
    int images[] = {R.drawable.one, R.drawable.two, R.drawable.three,R.drawable.four, R.drawable.fice,R.drawable.six,R.drawable.seven,R.drawable.password};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        setTitle("Article Feed");

        recyclerView = findViewById(R.id.recyclerView);
//        ExploreAdapterOop.RecyclerViewClickListener listener = new ExploreAdapterOop.RecyclerViewClickListener() {
//            @Override
//            public void onClick(View view, Integer mId) {
//                Intent intent = new Intent(ExploreActivity.this, ExploreDetail.class);
//                startActivity(intent);
//            }
//        };
//        mAdapter = new ExploreActivityOop(new ArrayList<Post>(), listener);


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
//                ArrayList<String> s1 = new ArrayList<String>();
//                for (int x = 0; x < entrys.size(); x++) {
//                    s1.add(entrys.get(x).getTitle());
//                }
               ArrayList<String> sTitle = new ArrayList<String>();
                ArrayList<String> sCreator = new ArrayList<String>();
                ArrayList<String> sPubDate = new ArrayList<String>();
                ArrayList<String> sLink = new ArrayList<String>();
                ArrayList<String> sDesc = new ArrayList<String>();
                ArrayList<String> sThumb = new ArrayList<String>();

                for (int i =0; i< entrys.size(); i++){
                    sTitle.add(entrys.get(i).getTitle());
                    sCreator.add(entrys.get(i).getCreator());
                    sPubDate.add(entrys.get(i).getPubDate().substring(0, 16));
                    sLink.add(entrys.get(i).getLink());
                    sDesc.add(entrys.get(i).getDescription());
                    sThumb.add(entrys.get(i).getThumbnail().getUrl());
                    Log.d(TAG, "onResponse: \n "+
                            "Title: " +entrys.get(i).getTitle() + "\n" +
                            "Creator: " +entrys.get(i).getCreator() + "\n" +
                            "pubDate: " +entrys.get(i).getPubDate() + "\n" +
                            "link: " +entrys.get(i).getLink() + "\n" +
                            "Desc: " +entrys.get(i).getDescription() + "\n" +
                            "Thumbnail: " +entrys.get(i).getThumbnail().getUrl() + "\n");
                }
                String[] s1 = new String[sTitle.size()];
                s1 = sTitle.toArray(s1);
                String[] s2 = new String[sCreator.size()];
                s2 = sCreator.toArray(s2);
                String[] s3 = new String[sPubDate.size()];
                s3 = sPubDate.toArray(s3);
                String[] s4 = new String[sLink.size()];
                s4 = sLink.toArray(s4);
                String[] s5 = new String[sDesc.size()];
                s5 = sDesc.toArray(s5);
                String[] s6 = new String[sThumb.size()];
                s6 = sThumb.toArray(s6);

//                ListView listView = (ListView) findViewById(R.id.recyclerView);
//                ExploreAdapterOop exploreAdapterOop = new ExploreAdapterOop(ExploreActivity.this, R.layout.explore_row, posts);
//                listView.setAdapter(exploreAdapterOop);


                ExploreAdapter exploreAdapter = new ExploreAdapter(ExploreActivity.this, s1, s2, s3, s4, s5, s6);
                recyclerView.setAdapter(exploreAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(ExploreActivity.this));
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