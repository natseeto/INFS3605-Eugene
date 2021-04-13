package com.example.infs3605;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.sun.jna.StringArray;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class ExploreActivity extends AppCompatActivity {

    private static final String TAG = "ExploreActivity";

    private static final String BASE_URL = "https://krebsonsecurity.com/";

    RecyclerView recyclerView;
    String s1[], s2[];
    int images[] = {R.drawable.malware, R.drawable.phishing, R.drawable.spear_phishing,R.drawable.mitm, R.drawable.trojan,R.drawable.ransomware,R.drawable.ddos,R.drawable.password};




    //connect in RSS feed per BPMN
    //recyclerview

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        FeedAPI feedAPI = retrofit.create(FeedAPI.class);

        Call<RSS> call = feedAPI.getFeed();

        call.enqueue(new Callback<RSS>() {
            @Override
            public void onResponse(Call<RSS> call, Response<RSS> response) {
                Log.d(TAG, "onResponse: feed: " + response.body().toString());
                Log.d(TAG, "onResponse: Server Response " + response.toString());
            }

            @Override
            public void onFailure(Call<RSS> call, Throwable t) {
                    Log.e(TAG, "onFailure: Unable to retrieve RSS: " +t.getMessage());
                Toast.makeText(ExploreActivity.this, "An Error Occurred", Toast.LENGTH_SHORT).show();
            }
        });

        s1 = getResources().getStringArray(R.array.titles);
        s2 = getResources().getStringArray(R.array.desc);

        recyclerView = findViewById(R.id.recyclerView);

       ExploreAdapter exploreAdapter = new ExploreAdapter(this, s1, s2, images);
        recyclerView.setAdapter(exploreAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void launchHome(String message) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}