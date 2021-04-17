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

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.MyViewHolder> {

    String data1[], data2[], data3[], data4[];
    int images[];
    Context context;
    public ExploreAdapter(Context ct, String s1[], String s2[], String s3[], int img[]) {
        context = ct;
        data1 = s1;
        data2 = s2;
        data3 = s3;
        //data4 = s4;
        images = img;

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
        holder.eDesc.setText(data2[position]);
        holder.eCreator.setText(data3[position]);
      //  holder.ePubDate.setText(data4[position]);
        holder.imageView.setImageResource(images[position]);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent(context, ExploreDetail.class);
                intent.putExtra("data1", data1[position]);
                intent.putExtra("data2", data2[position]);
                intent.putExtra("myImage", images[position]);
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
}
