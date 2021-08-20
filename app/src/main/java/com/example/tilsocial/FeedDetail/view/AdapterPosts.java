package com.example.tilsocial.FeedDetail.view;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class AdapterPosts extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context context;
    List<ModelPost> modelPosts;
    ActionBar actionBar;
    String postId,empId;
    private static int POST_VIEW = 0;
    private static int  INTEREST_VIEW = 1;
    int flag =0;
    List<String> taggs;
    ArrayList intersett ;
    List<String> interestList = new ArrayList<>();
    int empidinterger;


    public AdapterPosts(Context context,List<ModelPost> modelPosts,List<String> taggs,ArrayList intersett,int empidinterger) {
        this.context = context;
        this.modelPosts = modelPosts;
        this.taggs = taggs;
        this.intersett = intersett;
        this.empidinterger = empidinterger;



    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;


        if(viewType==POST_VIEW) {

            view = LayoutInflater.from(context).inflate(R.layout.feedcardview, parent, false);
            return new PostsHolder(view);
        }
        else{
            view= LayoutInflater.from(context).inflate(R.layout.taggss, parent, false);
            return new Interestholder(view);
        }



    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        Log.d("postionn", "onBindViewHolder: "+ position);
        if (getItemViewType(position) == POST_VIEW ) {

            PostsHolder holder1 = (PostsHolder) holder;
            ModelPost modelPost = modelPosts.get(position);
            holder1.name.setText(modelPost.getName());
            postId = modelPost.getPostId();
            Log.d("postidd", "onBindViewHolder: " + postId);
            empId = modelPost.getEmpId();
            holder1.like.setText(modelPost.getLikesCount() + " Likes");
            holder1.comments.setText(modelPost.getCommentsCount() + " Comments");
            Log.d("checkimg", "onBindViewHolder: " + modelPost.getEmpImgUrl());
            holder1.content.setText(modelPost.getContent());
            Glide.with(context).load(modelPost.getEmpImgUrl())
                    .placeholder(R.drawable.icprofile)
                    .error(R.drawable.ic_error_outline)
                    .into(holder1.userprof);
            String datetime= modelPost.getCreatedAt();
            Log.d("datetime", "onBindViewHolder: "+datetime);


            Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
            Log.d("Datetime_calender", "onBindViewHolder: "+calendar);
            try {
                calendar.setTimeInMillis(Long.parseLong(modelPost.getCreatedAt()));
                Log.d("Datetime_calender2", "onBindViewHolder: "+calendar);

            } catch (Exception ex) {
                ex.printStackTrace();
                Log.d("Datetime_calender3", "onBindViewHolder: "+calendar);

            }
            String timedate = DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();
//            Log.d("datetime_format", "onBindViewHolder: "+timedate);

//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.S",Locale.ENGLISH)
//                    .withZone(ZoneId.of("Etc/UTC"));
//
//            ZonedDateTime zdtUtc = ZonedDateTime.parse(datetime, formatter);
//
//            ZonedDateTime zdtInd = zdtUtc.withZoneSameInstant(ZoneId.of("Asia/Kolkata"));
//
//            DateTimeFormatter dtfOutput = DateTimeFormatter.ofPattern("MM-dd-uuuu hh:mm:ss a", Locale.ENGLISH);
//
//            Log.d("datetimeformatted", "onBindViewHolder: "+zdtInd.format(dtfOutput));
            holder1.time.setText(timedate);
            String taggs[] = modelPost.getTags();
            if (taggs == null) {
                holder1.tags.setText("Empty");
            } else {
                String tagg = "";
                for (int i = 0; i < taggs.length; i++) {
                    tagg = tagg + "#" + taggs[i] + " ";
                }
                holder1.tags.setText(tagg);
            }
            if(modelPost.getImages()==null||modelPost.getImages().isEmpty()||modelPost.getImages().get(0)==null)
            {
                holder1.imageView.setVisibility(View.GONE);
            }
            else
            {
                Glide.with(context).load(modelPost.getImages().get(0))
                        .placeholder(R.drawable.icprofile)
                        .error(R.drawable.ic_error_outline)
                        .into(holder1.imageView);

            }

            Log.d("imagecheckk", "onBindViewHolder: " +modelPost.getImages().get(0) );
            Log.d("contextcheck", "onBindViewHolder: " +context);


            holder1.comments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String postid;
                    postid=modelPost.getPostId();
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




        } else {

                Interestholder holder2 = (Interestholder) holder;
                  for(int j =0 ;j<taggs.size();j++)
                    {
                        if(intersett.contains(taggs.get(j)))
                        {
                            holder2.chip = new Chip(context);
                           // Log.d("intersets", "onBindViewHolder: " + taggs.get(i));
                            holder2.chip.setText(taggs.get(j));
                            holder2.chip.setCheckable(true);
                            holder2.chip.setChecked(true);
                            holder2.chip.setChipBackgroundColor(context.getResources().getColorStateList(R.color.color_state_chip_outline));
                            holder2.chipGroup.addView(holder2.chip);

                        }
                        else
                        {
                            holder2.chip = new Chip(context);
                            // Log.d("intersets", "onBindViewHolder: " + taggs.get(i));
                            holder2.chip.setText(taggs.get(j));
                            holder2.chip.setCheckable(true);
                            holder2.chip.setChipBackgroundColor(context.getResources().getColorStateList(R.color.color_state_chip_outline));
                            holder2.chipGroup.addView(holder2.chip);
                        }

                    }

                  interestList.addAll(intersett);

            Integer c = holder2.chipGroup.getChildCount();
            for (int j = 0; j < c; j++) {
                Chip chip = (Chip) holder2.chipGroup.getChildAt(j);
                chip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (chip.isChecked()) {
                            interestList.add(chip.getText().toString());
                        } else {
                            interestList.remove(chip.getText());
                        }
                        Log.d("intersetslistchecking", "onBindViewHolder: " + interestList.toString());
                     //Toast.makeText(context, "-" + interestList, Toast.LENGTH_LONG).show();
                    }
                });
            }
            Log.d("empidchecking", "onBindViewHolder: " + empidinterger);

            holder2.applybtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    HomeFragment homeFragment = new HomeFragment();
                    homeFragment.callsaveinterset(interestList,empidinterger,context);





                }
            });



        }


    }

    @Override
    public int getItemViewType(int position) {

        if (position == 2 ) {

            return INTEREST_VIEW;

        } else {
            return POST_VIEW;
        }
    }

    @Override
    public int getItemCount() {
        if(modelPosts.size() == 0)
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
        ImageView imageView,share, userprof;
        ChipGroup chipGroup;

        public PostsHolder(View itemView) {
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
            share=itemView.findViewById(R.id.sharebtn);
        }
    }

    class Interestholder extends RecyclerView.ViewHolder{

        ChipGroup chipGroup;
        Chip chip;
        Button applybtn;


        public Interestholder(@NonNull View itemView) {
            super(itemView);

            chipGroup = itemView.findViewById(R.id.intersetshow);
            applybtn = itemView.findViewById(R.id.applytagss);
        }
    }

}

