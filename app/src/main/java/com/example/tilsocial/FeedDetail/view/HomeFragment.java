package com.example.tilsocial.FeedDetail.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.tilsocial.FeedDetail.model.ModelPost;
import com.example.tilsocial.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    List<ModelPost> posts;
    AdapterPosts adapterPosts;




    public HomeFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.postrecyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        posts = new ArrayList<>();
        loadPosts();
        adapterPosts = new AdapterPosts(getActivity(), posts);
        recyclerView.setAdapter(adapterPosts);
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





    }

//    @Override
//    public void onCreate( Bundle savedInstanceState) {
//        setHasOptionsMenu(true);
//        super.onCreate(savedInstanceState);
//    }



}