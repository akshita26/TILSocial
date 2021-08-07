package com.example.tilsocial;

import android.app.ActionBar;
import android.app.Notification;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment {


    TextView bio,name,dept;
    RecyclerView recyclerView;
    UserPosts userPosts;
    List<ModelPost> posts;
    ImageView profile,editprof;
    ChipGroup chipGroup;
    Chip chip;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        name=view.findViewById(R.id.textView);
        dept=view.findViewById(R.id.textView4);
        bio=view.findViewById(R.id.bio);
        profile=view.findViewById(R.id.profile_image);
        editprof=view.findViewById(R.id.imageView2);
        chipGroup = view.findViewById(R.id.chip_group);
       // bio.setText("Shoot your own horn. Show off your achievements, give them a little personality, tell them what problem you’ll solve. Your bio helps you build a connection right from the start.");

        recyclerView = view.findViewById(R.id.recyid);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        posts = new ArrayList<>();
        loadPosts();
        userPosts = new UserPosts(getActivity(), posts);
        recyclerView.setAdapter(userPosts);

        editprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProfile editProfile = new EditProfile();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.add(R.id.dashboard, editProfile);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        //Interests
        String[] genres = {"Mobile Application Development", "Android", "iOS","System Design"};
        chipGroup.removeAllViews();
        for(String genre : genres) {
            chip = new Chip(getActivity());
            chip.setText(genre);

            chip.setChipBackgroundColor(getResources().getColorStateList(R.color.color_state_chip_outline));
            chip.setCheckable(true);
            chipGroup.addView(chip);
        }

        return view;
    }

    private void loadPosts() {


        ModelPost modelPost = new ModelPost();
        modelPost.setName("User1");
        modelPost.setDescription("MyDescription is Here");
        modelPost.setUimage("imageurl");
        modelPost.setUlike("20");
        modelPost.setUtime("1 min");
        modelPost.setUcomment("comments");
        modelPost.setUtitle("MYPOST");
        posts.add(modelPost);
        posts.add(modelPost);
        posts.add(modelPost);
        posts.add(modelPost);


    }
}