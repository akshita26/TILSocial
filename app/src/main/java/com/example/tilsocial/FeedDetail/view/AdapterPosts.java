package com.example.tilsocial.FeedDetail.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tilsocial.CommentFragment;
import com.example.tilsocial.FeedDetail.model.ModelPost;
import com.example.tilsocial.R;

import java.util.List;

public class AdapterPosts extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context context;
    List<ModelPost> modelPosts;
    ActionBar actionBar;
    Integer VIEWTYPE_POSTS=1;
    Integer VIEWTYPE_INTERESTS=2;


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
            holder1.description.setText(modelPost.getDescription());
            holder1.time.setText(modelPost.getUtime());
            holder1.like.setText("Likes " + modelPost.getUlike());

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

        TextView name, time, title, description, like, comments;

        public PostsHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.userprofilename);
            time = itemView.findViewById(R.id.timeofpost);
            description = itemView.findViewById(R.id.PostDescription);
            like = itemView.findViewById(R.id.nooflikepost);
            comments = itemView.findViewById(R.id.noofcomment);
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

