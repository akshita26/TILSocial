package com.example.tilsocial.FeedDetail.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tilsocial.CommentFragment;
import com.example.tilsocial.FeedDetail.model.ModelPost;
import com.example.tilsocial.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class AdapterPosts extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context context;
    List<ModelPost> modelPosts;
    ActionBar actionBar;
    Integer VIEWTYPE_POSTS=1;
    Integer VIEWTYPE_INTERESTS=2;
    Chip chip;


    public AdapterPosts(Context context, List<ModelPost> modelPosts) {
        this.context = context;
        this.modelPosts = modelPosts;

    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType==VIEWTYPE_POSTS) {
            view = LayoutInflater.from(context).inflate(R.layout.feedcardview, parent, false);
            return new PostsHolder(view);
        }
        else{
            view= LayoutInflater.from(context).inflate(R.layout.intersetcardview, parent, false);
            return new InterestHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(position == 3) {

            InterestHolder holder2 = (InterestHolder) holder;

        }
        else
        {
            PostsHolder holder1=(PostsHolder)holder;
            ModelPost modelPost = modelPosts.get(position);
            holder1.name.setText(modelPost.getName());
//            holder1.empid.setText("empid  " +modelPost.getEmpId());
            holder1.like.setText(modelPost.getLikesCount());
            holder1.comments.setText(modelPost.getCommentsCount());
            holder1.time.setText(modelPost.getCreatedAt());
            holder1.content.setText(modelPost.getContent());
            String taggs [] = modelPost.getTags();
            for(int i = 0 ; i<taggs.length; i++) {

                chip = new Chip(context);
                chip.setText(taggs[i]);
                chip.setTextColor(Color.WHITE);
                chip.setChipBackgroundColor(AppCompatResources.getColorStateList(context, R.color.color_tags_chip_state));
                holder1.chipGroup.addView(chip);
            }
            Glide.with(context).load(modelPost.getImgurl())
                    .placeholder(R.drawable.icprofile)
                    .error(R.drawable.ic_error_outline)
                    .into(holder1.imageView);

//         

            holder1.comments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    actionBar = ((AppCompatActivity) v.getContext()).getSupportActionBar();
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    actionBar.setTitle("Comment");
                    CommentFragment commentFragment = new CommentFragment();
                    FragmentManager fragmentManager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.dashboard, commentFragment);
                    fragmentTransaction.commit();
                }
            });

        }

    }

    @Override
    public int getItemViewType(int position) {
        if(position == 3){
            return VIEWTYPE_INTERESTS;
        }
        else
            return VIEWTYPE_POSTS;
    }

    @Override
    public int getItemCount() {

        return modelPosts.size()+1;
    }

    class PostsHolder extends RecyclerView.ViewHolder {

        TextView name, empid, content,like, comments,time,tags;
        ImageView imageView;
        ChipGroup chipGroup;

        public PostsHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.userprofilename);
            empid = itemView.findViewById(R.id.placeofpost);
            content = itemView.findViewById(R.id.PostDescription);
            like = itemView.findViewById(R.id.nooflikepost);
            comments = itemView.findViewById(R.id.noofcomment);
            imageView = itemView.findViewById(R.id.userPostimage);
            time = itemView.findViewById(R.id.timeofpost);
            chipGroup = itemView.findViewById(R.id.chip_groupfortags);
        }
    }

    class InterestHolder extends RecyclerView.ViewHolder {
        //
        TextView name, time, title, description, like, comments;

        public InterestHolder(View itemView) {
            super(itemView);

        }
    }




}

