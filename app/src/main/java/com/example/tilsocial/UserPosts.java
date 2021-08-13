package com.example.tilsocial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tilsocial.FeedDetail.model.ModelPost;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class UserPosts extends RecyclerView.Adapter<UserPosts.MyHolder>{
    Context context;
    List<ModelPost> modelPosts;


    public UserPosts(Context context, List<ModelPost> modelPosts) {
        this.context = context;
        this.modelPosts = modelPosts;

    }

    public UserPosts.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.feedcardview, parent, false);
        return new UserPosts.MyHolder(view);

    }

    @Override
    public void onBindViewHolder(UserPosts.MyHolder holder, int position) {

        ModelPost modelPost = modelPosts.get(position);
        holder.name.setText( modelPost.getName());
        holder.like.setText(modelPost.getLikesCount() + " Likes"  );
        holder.comments.setText(modelPost.getCommentsCount() + " Comments");
        holder.time.setText(modelPost.getCreatedAt());
        holder.content.setText(modelPost.getContent());
        String taggs [] = modelPost.getTags();
        String tagg = "";
        for(int i = 0 ; i<taggs.length; i++) {
            tagg = tagg + "#" + taggs[i] + " ";
        }
        holder.tags.setText(tagg);
        Glide.with(context).load(modelPost.getImgurl())
                .placeholder(R.drawable.icprofile)
                .error(R.drawable.ic_error_outline)
                .into(holder.imageView);





    }

    @Override
    public int getItemCount() {

        return modelPosts.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView name, content,like, comments,time,tags;
        ImageView imageView;
        ChipGroup chipGroup;

        public MyHolder( View itemView) {
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
