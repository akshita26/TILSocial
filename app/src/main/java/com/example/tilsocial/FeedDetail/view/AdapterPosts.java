package com.example.tilsocial.FeedDetail.view;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.google.android.material.chip.ChipGroup;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AdapterPosts extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context context;
    List<ModelPost> modelPosts;
    ActionBar actionBar;



    public AdapterPosts(Context context,List<ModelPost> modelPosts) {
        this.context = context;
        this.modelPosts = modelPosts;

    }



    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(context).inflate(R.layout.feedcardview, parent, false);
        return new PostsHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            PostsHolder holder1=(PostsHolder)holder;
            ModelPost modelPost = modelPosts.get(position);
            holder1.name.setText(modelPost.getName());
            holder1.like.setText(modelPost.getLikesCount() + " Likes"  );
            holder1.comments.setText(modelPost.getCommentsCount() + " Comments");
            holder1.content.setText(modelPost.getContent());
            Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
            try {
                calendar.setTimeInMillis(Long.parseLong(modelPost.getCreatedAt()));
            } catch(Exception ex) {
            ex.printStackTrace();
            }
            String timedate = DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();
            holder1.time.setText(timedate);
            String taggs [] = modelPost.getTags();
            String tagg = "";
            for(int i = 0 ; i<taggs.length; i++) {
                tagg = tagg + "#" + taggs[i] + " ";
            }
            holder1.tags.setText(tagg);
//            Glide.with(context).load(modelPost.getImages())
//                    .placeholder(R.drawable.icprofile)
//                    .error(R.drawable.ic_error_outline)
//                    .into(holder1.imageView);


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

    public void addtopost(List<ModelPost> modelPostList){
        if(modelPosts==null){
            modelPosts=modelPostList;
            notifyDataSetChanged();
        }
        else{
            modelPosts.addAll(modelPostList);
            notifyDataSetChanged();
        }
    }


    @Override
    public int getItemCount() {

        Log.e("Adaptor", "onResponse: " +  modelPosts.size());
        if (modelPosts == null)
            return 0;
        else
            return modelPosts.size();

    }

    class PostsHolder extends RecyclerView.ViewHolder {

        TextView name, content,like, comments,time,tags;
        ImageView imageView;
        ChipGroup chipGroup;

        public PostsHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.userprofilename);
//            empid = itemView.findViewById(R.id.placeofpost);
            content = itemView.findViewById(R.id.PostDescription);
            like = itemView.findViewById(R.id.nooflikepost);
            comments = itemView.findViewById(R.id.noofcomment);
            imageView = itemView.findViewById(R.id.userPostimage);
            time = itemView.findViewById(R.id.timeofpost);
            chipGroup = itemView.findViewById(R.id.chip_groupfortags);
            tags = itemView.findViewById(R.id.tagss);
        }
    }





}

