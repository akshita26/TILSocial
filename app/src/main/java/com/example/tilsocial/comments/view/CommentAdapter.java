package com.example.tilsocial.comments.view;


import android.content.Context;
import android.content.SharedPreferences;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tilsocial.R;
import com.example.tilsocial.comments.model.CommentModel;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyHolder>{



    Context context;
    List<CommentModel> commentss;
    SharedPreferences sharedPreferences;

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

        holder.commentuser.setText(commentModel.getName());
        holder.commentt.setText(commentModel.getComment());
        holder.designation.setText(commentModel.getDesignation());

        sharedPreferences= context.getSharedPreferences("details",0);
        Glide.with(context).load(sharedPreferences.getString("imgurl",""))
                .error(R.drawable.ic_error_outline)
                .into(holder.userimg);

        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        try {
            calendar.setTimeInMillis(Long.parseLong(commentModel.getCreatedAt()));
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        String timedate = DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();
        holder.time.setText(timedate);


    }

    @Override
    public int getItemCount() {

        return commentss.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        //        ImageView picture, image;
        TextView commentuser, commentt, time,designation;
        ImageView userimg;


        public MyHolder( View itemView) {
            super(itemView);

            commentuser = itemView.findViewById(R.id.commentname);
            commentt = itemView.findViewById(R.id.commenttext);
            time = itemView.findViewById(R.id.commenttime);
            userimg=itemView.findViewById(R.id.loadcomment);
            designation = itemView.findViewById(R.id.designationn);

        }
    }




}
