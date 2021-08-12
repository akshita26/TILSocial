package com.example.tilsocial;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyHolder>{



    Context context;
    List<CommentModel> commentss;


    public CommentAdapter(Context context, List<CommentModel> commentss) {
        this.context = context;
        this.commentss = commentss;

    }



    public CommentAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_comment, parent, false);
        return new CommentAdapter.MyHolder(view);

    }

    @Override
    public void onBindViewHolder(CommentAdapter.MyHolder holder, int position) {

        CommentModel commentModel = commentss.get(position);

        holder.commentuser.setText(commentModel.getUsername());
        holder.commentt.setText(commentModel.getComment());
        holder.time.setText(commentModel.getTime());
//        holder.name.setText( modelPost.getName());
////        holder.title.setText(modelPost.getUtitle());
//        holder.description.setText(modelPost.getDescription());
//        holder.time.setText(modelPost.getUtime());
//        holder.like.setText("Likes "+ modelPost.getUlike());





    }

    @Override
    public int getItemCount() {

        return commentss.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        //        ImageView picture, image;
        TextView commentuser;
        TextView commentt;
        TextView time;


        public MyHolder( View itemView) {
            super(itemView);

            commentuser = itemView.findViewById(R.id.commentname);
            commentt = itemView.findViewById(R.id.commenttext);
            time = itemView.findViewById(R.id.commenttime);

        }
    }




}
