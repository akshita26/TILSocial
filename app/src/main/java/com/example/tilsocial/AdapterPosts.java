package com.example.tilsocial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterPosts extends RecyclerView.Adapter<AdapterPosts.MyHolder>{

    Context context;
    List<ModelPost> modelPosts;


    public AdapterPosts(Context context, List<ModelPost> modelPosts) {
        this.context = context;
        this.modelPosts = modelPosts;

    }



    public MyHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.postcardview, parent, false);
        return new MyHolder(view);

    }

    @Override
    public void onBindViewHolder( MyHolder holder, int position) {

        ModelPost modelPost = modelPosts.get(position);
        holder.name.setText( modelPost.getName());
        holder.title.setText(modelPost.getUtitle());
        holder.description.setText(modelPost.getDescription());
        holder.time.setText(modelPost.getUtime());




    }

    @Override
    public int getItemCount() {

        return modelPosts.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
//        ImageView picture, image;
        TextView name, time, title, description, like, comments;
//        ImageButton more;
//        Button likebtn, comment;
//        LinearLayout profile;

        public MyHolder( View itemView) {
            super(itemView);
//            picture = itemView.findViewById(R.id.picturetv);
//            image = itemView.findViewById(R.id.pimagetv);
            name = itemView.findViewById(R.id.unametv);
            time = itemView.findViewById(R.id.utimetv);
//            more = itemView.findViewById(R.id.morebtn);
            title = itemView.findViewById(R.id.ptitletv);
            description = itemView.findViewById(R.id.descript);
            like = itemView.findViewById(R.id.plikeb);
            comments = itemView.findViewById(R.id.pcommentco);
//            likebtn = itemView.findViewById(R.id.like);
//            comment = itemView.findViewById(R.id.comment);
//            profile = itemView.findViewById(R.id.profilelayout);
        }
    }




}
