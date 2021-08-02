package com.example.tilsocial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterPosts extends RecyclerView.Adapter<AdapterPosts.MyHolder>{

    Context context;
    List<ModelPost> modelPosts;
    ActionBar actionBar;


    public AdapterPosts(Context context, List<ModelPost> modelPosts) {
        this.context = context;
        this.modelPosts = modelPosts;

    }



    public MyHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.feedcardview, parent, false);
        return new MyHolder(view);

    }

    @Override
    public void onBindViewHolder( MyHolder holder, int position) {

        ModelPost modelPost = modelPosts.get(position);
        holder.name.setText( modelPost.getName());
//        holder.title.setText(modelPost.getUtitle());
        holder.description.setText(modelPost.getDescription());
        holder.time.setText(modelPost.getUtime());
        holder.like.setText("Likes "+ modelPost.getUlike());

        holder.comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                actionBar = ((AppCompatActivity) v.getContext()).getSupportActionBar();
                actionBar.setDisplayHomeAsUpEnabled(true);

                actionBar.setTitle("Comment");
                CommentFragment commentFragment = new CommentFragment();
                FragmentManager fragmentManager =((FragmentActivity) v.getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.dashboard, commentFragment);
                fragmentTransaction.commit();

            }
        });





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
            name = itemView.findViewById(R.id.userprofilename);
            time = itemView.findViewById(R.id.timeofpost);
//            more = itemView.findViewById(R.id.morebtn);
//            title = itemView.findViewById(R.id.PostDescription);
            description = itemView.findViewById(R.id.PostDescription);
            like = itemView.findViewById(R.id.nooflikepost);
            comments = itemView.findViewById(R.id.noofcomment);
//            likebtn = itemView.findViewById(R.id.like);
//            comment = itemView.findViewById(R.id.comment);
//            profile = itemView.findViewById(R.id.profilelayout);
        }
    }




}
