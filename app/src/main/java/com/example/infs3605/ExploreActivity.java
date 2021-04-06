package com.example.infs3605;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.sun.jna.StringArray;

public class ExploreActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String s1[], s2[];
    int images[] = {R.drawable.malware, R.drawable.phishing, R.drawable.spear_phishing,R.drawable.mitm, R.drawable.trojan,R.drawable.ransomware,R.drawable.ddos,R.drawable.password};




    //connect in RSS feed per BPMN
    //recyclerview

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

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