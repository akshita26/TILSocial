package com.example.tilsocial.FeedDetail.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
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

import com.bumptech.glide.Glide;
import com.example.tilsocial.FeedDetail.model.ModelPost;
import com.example.tilsocial.R;
import com.example.tilsocial.comments.view.CommentFragment;
import com.google.android.material.chip.ChipGroup;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AdapterPosts extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context context;
    List<ModelPost> modelPosts;
    ActionBar actionBar;
    String postId,empId;


    public AdapterPosts(Context context,List<ModelPost> modelPosts) {
        this.context = context;
        this.modelPosts = modelPosts;

    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
//        if(viewType==VIEWTYPE_POSTS) {
//
//        }
//        else{
//            view= LayoutInflater.from(context).inflate(R.layout.intersetcardview, parent, false);
//            return new InterestHolder(view);
//        }

        view = LayoutInflater.from(context).inflate(R.layout.feedcardview, parent, false);
        return new PostsHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        if(position == 2) {
//
//            InterestHolder holder2 = (InterestHolder) holder;
//
//        }
//        else
//        {
            PostsHolder holder1=(PostsHolder)holder;
            ModelPost modelPost = modelPosts.get(position);
            holder1.name.setText(modelPost.getName());
            postId=modelPost.getPostId();
            empId=modelPost.getEmpId();
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
            if(taggs==null){
                holder1.tags.setText("Empty");
            }
            else {
                String tagg = "";
                for (int i = 0; i < taggs.length; i++) {
                    tagg = tagg + "#" + taggs[i] + " ";
                }
                holder1.tags.setText(tagg);
            }

               Glide.with(context).load(modelPost.getImages().get(0))
                       .placeholder(R.drawable.icprofile)
                       .error(R.drawable.ic_error_outline)
                       .into(holder1.imageView);








//

            holder1.comments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String postid = modelPost.getPostId();
                    actionBar = ((AppCompatActivity) v.getContext()).getSupportActionBar();
                    actionBar.setTitle("Comment");
                    CommentFragment commentFragment = new CommentFragment();
                    Bundle bundle=new Bundle();
                    bundle.putString("postid", postid);
                    commentFragment.setArguments(bundle);
                    FragmentManager fragmentManager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.dashboard, commentFragment);
                    fragmentTransaction.commit();
                }
            });

            holder1.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, modelPost.getContent());
                    shareIntent.putExtra(Intent.EXTRA_STREAM, modelPost.getImages().get(0));
                    shareIntent.setType("image/*");
                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    context.startActivity(Intent.createChooser(shareIntent, "Share post..."));
                }
            });




    }

//    public void addtopost(List<ModelPost> modelPostList){
//        if(modelPosts==null){
//            modelPosts=modelPostList;
//            notifyDataSetChanged();
//        }
//        else{
//            modelPosts.addAll(modelPostList);
//            notifyDataSetChanged();
//        }
//    }

    @Override
    public int getItemCount() {
        if(modelPosts.size()==0)
        {
            return 0;
        }
        else
        {
            return modelPosts.size();
        }



    }

    class PostsHolder extends RecyclerView.ViewHolder {

        TextView name, content,like, comments,time,tags;
        ImageView imageView,share;
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
            share=itemView.findViewById(R.id.sharebtn);
        }
    }

}

