package com.example.tilsocial.comments.view;


import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tilsocial.R;
import com.example.tilsocial.comments.model.CommentModel;

import java.util.Calendar;
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

    @Override
    public void onBindViewHolder(CommentAdapter.MyHolder holder, int position) {

        CommentModel commentModel = commentss.get(position);

        holder.commentuser.setText(commentModel.getName());
        holder.commentt.setText(commentModel.getComment());

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
