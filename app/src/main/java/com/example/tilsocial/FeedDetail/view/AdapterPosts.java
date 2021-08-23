package com.example.tilsocial.FeedDetail.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
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
import com.example.tilsocial.FeedDetail.api.ApiClient;
import com.example.tilsocial.FeedDetail.api.ApiInterface;
import com.example.tilsocial.FeedDetail.model.ModelPost;
import com.example.tilsocial.R;
import com.example.tilsocial.comments.view.CommentFragment;
import com.example.tilsocial.likes.model.LikeModel;
import com.example.tilsocial.likes.model.PostLike;
import com.example.tilsocial.likes.presenter.LikePresenter;
import com.example.tilsocial.likes.view.LikeView;
import com.example.tilsocial.profile.ColleagueProfile;
import com.example.tilsocial.signin.data.SigninAPIClient;
import com.example.tilsocial.signin.data.SigninAPIinterface;
import com.example.tilsocial.signin.model.ErrorResponse;
import com.example.tilsocial.signin.model.ErrorUtils;
import com.example.tilsocial.signin.model.UserData;
import com.example.tilsocial.signup.view.SignUpFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.net.URI;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterPosts extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context context;
    List<ModelPost> modelPosts;
    ActionBar actionBar;
    String postId,empId;
    private static int POST_VIEW = 0;
    private static int  INTEREST_VIEW = 1;
    List<String> tagss = new ArrayList<>();
    LikeView likeView;
    int flag =0;
    List<String> taggs;
    ArrayList intersett ;
    List<String> interestList = new ArrayList<>();
    int empidinterger;
    ApiInterface apiInterface;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    public AdapterPosts(Context context, List<ModelPost> modelPosts, List<String> taggs, ArrayList intersett, int empidinterger) {
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        Log.d("postionn", "onBindViewHolder: "+ position);
        if (getItemViewType(position) == POST_VIEW ) {

            PostsHolder holder1 = (PostsHolder) holder;
            ModelPost modelPost = modelPosts.get(position);
            holder1.name.setText(modelPost.getName());
            holder1.desgination.setText(modelPost.getDesignation());
            modelPost.setHasLiked(modelPost.getHasLiked());
            Log.d("postidd", "onBindViewHolder: " + postId);
            holder1.like.setText(modelPost.getLikesCount() + " Likes");
            Log.d("postidd", "onBindViewHolder: " + postId);
            likeView=new LikeView(new LikePresenter(new LikeModel()));
            holder1.like.setText(modelPost.getLikesCount()+ " Likes");
            Log.d("modelpostt", "Liked: " + modelPost.getHasLiked());
            if(modelPost.getHasLiked()==true) {
                holder1.likeimage.setColorFilter(Color.rgb(0, 0, 255));


            }
            else{
                holder1.likeimage.setColorFilter(Color.rgb(55, 71, 79));
            }

            holder1.likeimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(modelPost.getHasLiked()==false) {
                        Log.e("LikePosteeeee", "falseeeeeee: " );
                        holder1.likeimage.setColorFilter(Color.rgb(0, 0, 255));
                        PostLike postLike=new PostLike();
                        postId = modelPost.getPostId();
                        postLike.setPostId(postId);
                        postLike.setEmpId(empidinterger);
                        modelPost.setHasLiked(true);
                        Log.e("LikePosteeeee", "falseeeeeee: "+postId );
                        Log.e("LikePosteeeee", "falseeeeeee: "+empId );
                        likeView.postlike(postLike);
                        holder1.like.setText(Integer.parseInt(modelPost.getLikesCount())+1+ " Likes");
                        modelPost.setLikesCount(Integer.toString(Integer.parseInt(modelPost.getLikesCount())+1));
                        notifyItemChanged(position);
                    }
                    else{
                        Log.e("LikePosteeee", "trueeeeeeeeeeeee: " );
                        holder1.likeimage.setColorFilter(Color.rgb(55, 71, 79));
                        PostLike postLike=new PostLike();
                        postId = modelPost.getPostId();
                        postLike.setPostId(postId);
                        postLike.setEmpId(empidinterger);
                        likeView.postlike(postLike);
                        modelPost.setHasLiked(false);
                        holder1.like.setText(Integer.parseInt(modelPost.getLikesCount())-1+ " Likes");
                        modelPost.setLikesCount(Integer.toString(Integer.parseInt(modelPost.getLikesCount())-1));
                        notifyItemChanged(position);
                    }

                }
            });
            holder1.comments.setText(modelPost.getCommentsCount() + " Comments");
            Log.d("checkimg", "onBindViewHolder: " + modelPost.getEmpImgUrl());
            holder1.content.setText(modelPost.getContent());
            Glide.with(context).load(modelPost.getEmpImgUrl())
                    .placeholder(R.drawable.profile)
                    .error(R.drawable.profile)
                    .into(holder1.userprof);

            String datetime= modelPost.getCreatedAt();
            Log.d("datetime", "onBindViewHolder: "+datetime);

//
//            Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
//            Log.d("Datetime_calender", "onBindViewHolder: "+calendar);
//            try {
//                calendar.setTimeInMillis(Long.parseLong(modelPost.getCreatedAt()));
//                Log.d("Datetime_calender2", "onBindViewHolder: "+calendar);
//
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                Log.d("Datetime_calender3", "onBindViewHolder: "+calendar);
//
//            }
//            String timedate = DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();
//            Log.d("datetime_format", "onBindViewHolder: "+timedate);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]",Locale.ENGLISH)
                    .withZone(ZoneId.of("Etc/UTC"));

            ZonedDateTime zdtUtc = ZonedDateTime.parse(datetime, formatter);

            ZonedDateTime zdtInd = zdtUtc.withZoneSameInstant(ZoneId.of("Asia/Kolkata"));

            DateTimeFormatter dtfOutput = DateTimeFormatter.ofPattern("dd-MM-uuuu hh:mm a", Locale.ENGLISH);

            Log.d("datetimeformatted", "onBindViewHolder: "+zdtInd.format(dtfOutput));
            holder1.time.setText(zdtInd.format(dtfOutput));

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
                try{
                    Glide.with(context).load(modelPost.getImages().get(0))
                            .placeholder(R.drawable.noimageeee)
                            .error(R.drawable.noimageeee)
                            .into(holder1.imageView);

                }
                catch (Exception e) {

                }

            }

            holder1.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageFragment imageFragment= new ImageFragment();
                    Bundle bundle=new Bundle();
                    bundle.putString("postimg", modelPost.getImages().get(0));
                    imageFragment.setArguments(bundle);

                    FragmentManager fragmentManager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                    fragmentTransaction.add(R.id.dashboard, imageFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.add(android.R.id.content, imageFragment).commit();

//                    imageFragment.show(fragmentTransaction, "dialog");
                }
            });

            holder1.commentimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String postid;
                    postid=modelPost.getPostId();
                    actionBar = ((AppCompatActivity) v.getContext()).getSupportActionBar();
                    actionBar.setTitle("Comment");
                    CommentFragment commentFragment = new CommentFragment();
                     Bundle bundle=new Bundle();
                     bundle.putString("postid", postid);
                     bundle.putString("comments", modelPost.getCommentsCount());
                     bundle.putInt("commentposition",position);
                     commentFragment.setArguments(bundle);

                     FragmentManager fragmentManager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                     FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                      fragmentTransaction.hide(((FragmentActivity) v.getContext()).getSupportFragmentManager().findFragmentById(R.id.dashboard));
                     fragmentTransaction.add(R.id.dashboard, commentFragment);
                     fragmentTransaction.addToBackStack(null);
                     fragmentTransaction.commit();


                    // notifyItemChanged(position);
                    //Log.d("commentcountt", "onBindViewHolder: " +modelPosts.get(position).getCommentsCount());

                }
            });

            holder1.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, modelPost.getContent());
                    shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(modelPost.getImages().get(0)));
                    Log.d("sharecheck", "onClick: "+Uri.parse(modelPost.getImages().get(0)));
                    shareIntent.setType("image/jpg");
//                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    context.startActivity(Intent.createChooser(shareIntent, "Share post..."));
                }
            });

   //colleague profile
            holder1.userprof.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    apiInterface = ApiClient.getClient().create(ApiInterface.class);

                    int EmployeeId=Integer.parseInt(modelPost.getEmpId());

                    Call<UserData> GetCall = apiInterface.getColleagueProf(EmployeeId);

                    GetCall.enqueue(new Callback<UserData>() {
                        @Override
                        public void onResponse(Call<UserData> call, Response<UserData> response) {
                            if(response.isSuccessful())
                            {
                                Log.e("Employeeid", "onResponse: " + response.body().getEmpId());
                                Log.e("Employeename", "onResponse: " + response.body().getName());
                                sharedPreferences = context.getSharedPreferences("colleague", 0);
                                editor = sharedPreferences.edit();
                                editor.putString("empid",response.body().getEmpId().toString());
                                editor.putString("name", response.body().getName());
                                editor.putString("dept", response.body().getDept());
                                editor.putString("bio", response.body().getBio());
                                editor.putString("desig", response.body().getDesignation());
                                HashSet<String> set = new HashSet(response.body().getInterests());
                                editor.putStringSet("inter", set);
                                editor.putString("team", response.body().getTeam());
                                editor.putString("imgurl",response.body().getImgUrl());
                                Log.d("profilepicture", "setDataToRecyclerView: "+response.body().getInterests());
                                editor.commit();

                                ColleagueProfile colleagueProfile = new ColleagueProfile();
                                FragmentManager fragmentManager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.hide(((FragmentActivity) v.getContext()).getSupportFragmentManager().findFragmentById(R.id.dashboard));
                                fragmentTransaction.add(R.id.dashboard, colleagueProfile);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();

                            }
                            else
                            {
                                ErrorResponse errorResponse = ErrorUtils.parseError(response);
                                Log.d("Errorhandling", "onResponse: "+errorResponse.getError());
                            }

                        }

                        @Override
                        public void onFailure(Call<UserData> call, Throwable t) {
                            Log.e("Failure", "onResponse: " + t.getMessage() );

                        }
                    });

                }
            });

        } else if(flag == 0) {

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
                            holder2.chip.setElevation(5F);
                            holder2.chipGroup.addView(holder2.chip);

                        }
                        else
                        {
                            holder2.chip = new Chip(context);
                            // Log.d("intersets", "onBindViewHolder: " + taggs.get(i));
                            holder2.chip.setText(taggs.get(j));
                            holder2.chip.setCheckable(true);
                            holder2.chip.setChipBackgroundColor(context.getResources().getColorStateList(R.color.color_state_chip_outline));
                            holder2.chip.setTextAppearanceResource(R.style.ChipTextStyle_Selected);
                            holder2.chip.setElevation(5F);
                            holder2.chipGroup.addView(holder2.chip);
                        }
                    }
                  flag = 1;

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

        if (position == 5 ) {

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

    Boolean hasLiked;
    Integer likesCount;

    public void likeresponse(PostLike postLike) {
//        hasLiked=postLike.getHasLiked();
//        likesCount=postLike.getLikesCount();
    }



    class PostsHolder extends RecyclerView.ViewHolder {

        TextView name, content,like, comments,time,tags, desgination;
        ImageView imageView,share, userprof,commentimg, likeimage;
        ChipGroup chipGroup;

        public PostsHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.userprofilename);
            desgination = itemView.findViewById(R.id.Designation);
//            empid = itemView.findViewById(R.id.placeofpost);
            content = itemView.findViewById(R.id.PostDescription);
            like = itemView.findViewById(R.id.nooflikepost);
            likeimage = itemView.findViewById(R.id.likeimagepost);
            comments = itemView.findViewById(R.id.noofcomment);
            commentimg=itemView.findViewById(R.id.commentimgbtn);
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



