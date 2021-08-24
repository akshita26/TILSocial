package com.example.tilsocial.comments.view;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tilsocial.R;
import com.example.tilsocial.comments.model.CommentModel;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(CommentAdapter.MyHolder holder, int position) {

        CommentModel commentModel = commentss.get(position);

        holder.commentuser.setText(commentModel.getName());
        holder.commentt.setText(commentModel.getComment());
        holder.designation.setText(commentModel.getDesignation());

        Glide.with(context).load(commentModel.getEmpImgUrl())
                .error(R.drawable.ic_error_outline)
                .into(holder.userimg);

        String datetime= commentModel.getCreatedAt();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]",Locale.ENGLISH)
                .withZone(ZoneId.of("Etc/UTC"));

        ZonedDateTime zdtUtc = ZonedDateTime.parse(datetime, formatter);

        ZonedDateTime zdtInd = zdtUtc.withZoneSameInstant(ZoneId.of("Asia/Kolkata"));

        DateTimeFormatter dtfOutput = DateTimeFormatter.ofPattern("dd-MM-uuuu hh:mm a", Locale.ENGLISH);

        Log.d("datetimeformatted", "onBindViewHolder: "+zdtInd.format(dtfOutput));
        holder.time.setText(zdtInd.format(dtfOutput));


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
