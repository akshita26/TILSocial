package com.example.tilsocial;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tilsocial.FeedDetail.model.ModelPost;
import com.example.tilsocial.FeedDetail.view.ImageFragment;
import com.google.android.material.chip.ChipGroup;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.ocpsoft.prettytime.PrettyTime;

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(UserPosts.MyHolder holder, int position) {

        ModelPost modelPost = modelPosts.get(position);
        holder.name.setText( modelPost.getName());
        holder.designation.setText(modelPost.getDesignation());
        holder.like.setText(modelPost.getLikesCount() + " Likes"  );
        holder.comments.setText(modelPost.getCommentsCount() + " Comments");
//        holder.time.setText(modelPost.getCreatedAt());
        holder.content.setText(modelPost.getContent());
        Glide.with(context).load(modelPost.getEmpImgUrl())
                .placeholder(R.drawable.icprofile)
                .error(R.drawable.ic_error_outline)
                .into(holder.userprof);
        String taggs [] = modelPost.getTags();
        String tagg = "";
        for(int i = 0 ; i<taggs.length; i++) {
            tagg = tagg + "#" + taggs[i] + " ";
        }
        holder.tags.setText(tagg);

        if(modelPost.getImages()==null||modelPost.getImages().isEmpty()||modelPost.getImages().get(0)==null)
        {
            holder.imageView.setVisibility(View.GONE);
        }
        else
        {
            try{
                Glide.with(context).load(modelPost.getImages().get(0))
                        .placeholder(R.drawable.noimageeee)
                        .error(R.drawable.noimageeee)
                        .into(holder.imageView);

            }
            catch (Exception e) {

            }
        }

        String datetime= modelPost.getCreatedAt();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]", Locale.ENGLISH)
                .withZone(ZoneId.of("Etc/UTC"));

        ZonedDateTime zdtUtc = ZonedDateTime.parse(datetime, formatter);

        ZonedDateTime zdtInd = zdtUtc.withZoneSameInstant(ZoneId.of("Asia/Kolkata"));

        DateTimeFormatter dtfOutput = DateTimeFormatter.ofPattern("dd-MM-uuuu hh:mm a", Locale.ENGLISH);

        Log.d("datetimeformatted", "onBindViewHolder: "+zdtInd.format(dtfOutput));
        holder.time.setText(zdtInd.format(dtfOutput));

        Log.d("TAGDate", "onBindViewHolder: "+zdtInd.format(dtfOutput));

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageFragment imageFragment= new ImageFragment();
                Bundle bundle=new Bundle();
                bundle.putString("postimg", modelPost.getImages().get(0));
                imageFragment.setArguments(bundle);

                FragmentManager fragmentManager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                imageFragment.show(fragmentTransaction, "dialog");
            }
        });

    }

    @Override
    public int getItemCount() {

        return modelPosts.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView name, content,like, comments,time,tags, designation;
        ImageView imageView, userprof;
        ChipGroup chipGroup;

        public MyHolder( View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.userprofilename);
//            empid = itemView.findViewById(R.id.placeofpost);
            content = itemView.findViewById(R.id.PostDescription);
            like = itemView.findViewById(R.id.nooflikepost);
            comments = itemView.findViewById(R.id.noofcomment);
            imageView = itemView.findViewById(R.id.userPostimage);
            userprof=itemView.findViewById(R.id.userprofileimg);
            time = itemView.findViewById(R.id.timeofpost);
            chipGroup = itemView.findViewById(R.id.chip_groupfortags);
            tags = itemView.findViewById(R.id.tagss);
            designation=itemView.findViewById(R.id.Designation);
        }
    }
}
