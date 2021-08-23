package com.example.tilsocial.FeedDetail.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tilsocial.FeedDetail.model.ModelPost;
import com.example.tilsocial.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class RecommendationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<String> modelPosts;
    Context c;

    public RecommendationAdapter(List<String> modelPosts, Context c) {
        this.modelPosts = modelPosts;
        this.c=c;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommendation, parent, false);
        return new RecommendHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecommendHolder holder1 = (RecommendHolder) holder;
        holder1.name.setText("Akshita");
        holder1.desig.setText("Developer");
    }

    @Override
    public int getItemCount() {
        return modelPosts.size();
    }

    class RecommendHolder extends RecyclerView.ViewHolder{

        ImageView prof;
        TextView name,desig;

        public RecommendHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            desig=itemView.findViewById(R.id.designate);


        }
    }
}
