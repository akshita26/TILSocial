package com.example.tilsocial;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class CommentFragment extends Fragment {


    RecyclerView recyclerViewcomment;
    List<CommentModel> commentss;
    CommentAdapter commentAdapter;


    public CommentFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //comment adding
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_comment, container, false);
        recyclerViewcomment = view.findViewById(R.id.commentrecyclerview);
        recyclerViewcomment.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerViewcomment.setLayoutManager(layoutManager);
        commentss = new ArrayList<>();
        loadComments();
        commentAdapter = new CommentAdapter(getActivity(), commentss);
        recyclerViewcomment.setAdapter(commentAdapter);

        return view;


    }


    private void loadComments() {
        CommentModel commentModel = new CommentModel();
        commentModel.setUsername("Manpreet");
        commentModel.setComment("Good Job");
        commentModel.setTime("30 min ago");
        commentss.add(commentModel);
        commentss.add(commentModel);
        commentss.add(commentModel);

    }

}