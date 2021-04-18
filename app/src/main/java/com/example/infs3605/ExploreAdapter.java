package com.example.infs3605;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.MyViewHolder> {

    String data1[], data2[], data3[], data4[], data5[], data6[];

    int images[];
    Context context;
    List<Post> mPost;

    public ExploreAdapter(Context ct, String[] s1, String[] s2, String[] s3, String[] s4, String[] s5, String[] s6) {


                context = ct;
        data1 = s1;
        data2 = s2;
        data3 = s3;
        data4 = s4;
        data5 = s5;
        data6 = s6;

//        data4 = s4;
//        images = img;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.explore_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.eTitle.setText(data1[position]);
        holder.eDesc.setText(data3[position]);
        holder.eCreator.setText(data2[position]);
//       holder.imageView.setImageResource(images[position]);

        String currentURL = data6[position];
        Glide.with(context).load(currentURL).into(holder.imageView);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent(context, ExploreDetail.class);
                intent.putExtra("data1", data1[position]);
                intent.putExtra("data2", data2[position]);
                intent.putExtra("data3", data3[position]);
                intent.putExtra("data4", data4[position]);
                intent.putExtra("data5", data5[position]);
                intent.putExtra("data6", data6[position]);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView eTitle, eDesc, eCreator;
        ImageView imageView;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            eTitle = itemView.findViewById(R.id.eTitle);
            eDesc = itemView.findViewById(R.id.ePubDate);
            eCreator = itemView.findViewById(R.id.eCreator);
           imageView = itemView.findViewById(R.id.imageView);
           mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
//    public void setThumb (String addy, ImageView imageView){
//        Glide.with(this).load(addy).into(imageView);
//    }
}
