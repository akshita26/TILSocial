package com.example.tilsocial.FeedDetail.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tilsocial.FeedDetail.model.ModelPost;
import com.example.tilsocial.R;
import com.example.tilsocial.profile.ColleagueProfile;
import com.example.tilsocial.signin.model.UserData;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RecommendationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<UserData> recomData;
    Context c;

    public RecommendationAdapter(List<UserData> recomData, Context c) {
        this.recomData = recomData;
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
        UserData userData= recomData.get(position);
        holder1.name.setText(userData.getName());
        holder1.desig.setText(userData.getDesignation());
            try{
                Glide.with(c).load(userData.getImgUrl())
                        .placeholder(R.drawable.profile)
                        .error(R.drawable.profile)
                        .into(holder1.prof);

            }
            catch (Exception e) {

            }



    }

    @Override
    public int getItemCount() {
        return recomData.size();
    }

    class RecommendHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView prof;
        TextView name,desig;

        public RecommendHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            desig=itemView.findViewById(R.id.designate);
            prof=itemView.findViewById(R.id.imageView2);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            SharedPreferences sharedPreferences;
            SharedPreferences.Editor editor;
            UserData userData=recomData.get(getAdapterPosition());
            sharedPreferences = c.getSharedPreferences("colleague", 0);
            editor = sharedPreferences.edit();
            editor.putString("empid",userData.getEmpId().toString());
            editor.putString("name",userData.getName());
            editor.putString("dept", userData.getDept());
            editor.putString("bio", userData.getBio());
            editor.putString("desig", userData.getDesignation());
            HashSet<String> set = new HashSet(userData.getInterests());
            editor.putStringSet("inter", set);
            editor.putString("team", userData.getTeam());
            editor.putString("imgurl",userData.getImgUrl());
            editor.commit();

            ColleagueProfile colleagueProfile = new ColleagueProfile();
            FragmentManager fragmentManager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.hide(((FragmentActivity) v.getContext()).getSupportFragmentManager().findFragmentById(R.id.dashboard));
            fragmentTransaction.add(R.id.dashboard, colleagueProfile);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}
