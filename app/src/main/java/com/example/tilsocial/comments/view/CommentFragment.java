package com.example.tilsocial.comments.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tilsocial.R;
import com.example.tilsocial.comments.API.CommentAPIInterface;
import com.example.tilsocial.comments.model.CommentModel;
import com.example.tilsocial.comments.model.ModelComment;
import com.example.tilsocial.comments.model.PostComment;
import com.example.tilsocial.comments.presenter.CommentPresenter;
import com.example.tilsocial.comments.presenter.MainContractComment;

import java.util.ArrayList;
import java.util.List;

public class CommentFragment extends Fragment implements MainContractComment.MainView {


    RecyclerView recyclerViewcomment;
    List<CommentModel> commentss;
    CommentAdapter commentAdapter;
    CommentAPIInterface apiInterface;
    private MainContractComment.presenter presenter;
    String postId, empId;
    ImageView sendcomment;
    EditText newcomment;

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
        presenter = new CommentPresenter(this,new ModelComment());
        sendcomment=view.findViewById(R.id.sendcomment);
        newcomment=view.findViewById(R.id.editTextTextPersonName4);
        recyclerViewcomment = view.findViewById(R.id.commentrecyclerview);
        recyclerViewcomment.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewcomment.setLayoutManager(layoutManager);
        Bundle bundle=this.getArguments();
        postId= bundle.getString("postid");
        empId= bundle.getString("empid");
        Log.d("posttag", "onCreateView: "+postId);
        commentss = new ArrayList<>();
        loadComments();
        commentAdapter = new CommentAdapter(getActivity(), commentss);
        recyclerViewcomment.setAdapter(commentAdapter);

        sendcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostComment postComment=new PostComment();
                postComment.setComment(newcomment.getText().toString());
                postComment.setPostId(postId);
                int empid = Integer.parseInt(empId);
                postComment.setEmpId(empid);
                presenter.postcomment(postComment);
            }
        });
        return view;
    }

    private void loadComments() {
        presenter.requestdatafromserver(postId);

    }

    @Override
    public void setDataToRecyclerViewComment(List<CommentModel> commentModelList) {

        commentss.addAll(commentModelList);
        commentAdapter.notifyDataSetChanged();
//        Log.d("TAG", "setDataToRecyclerViewComment: "+commentModelList);
    }

    @Override
    public void onResponseFailure(Throwable t) {

    }

    @Override
    public void SetNewComment(PostComment body) {
        Toast.makeText(getActivity(), "Comment Posted", Toast.LENGTH_SHORT).show();
    }
}