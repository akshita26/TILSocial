package com.example.tilsocial.comments.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tilsocial.FeedDetail.model.MainFeedModel;
import com.example.tilsocial.FeedDetail.presentor.FeedPresentor;
import com.example.tilsocial.FeedDetail.presentor.MainContract;
import com.example.tilsocial.R;
import com.example.tilsocial.comments.API.CommentAPIClient;
import com.example.tilsocial.comments.API.CommentAPIInterface;
import com.example.tilsocial.comments.model.CommentModel;
import com.example.tilsocial.comments.model.ModelComment;
import com.example.tilsocial.comments.presenter.CommentPresenter;
import com.example.tilsocial.comments.presenter.MainContractComment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentFragment extends Fragment implements MainContractComment.MainView {


    RecyclerView recyclerViewcomment;
    List<CommentModel> commentss;
    CommentAdapter commentAdapter;
    CommentAPIInterface apiInterface;
    private MainContractComment.presenter presenter;
    String postId;

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
        recyclerViewcomment = view.findViewById(R.id.commentrecyclerview);
        recyclerViewcomment.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerViewcomment.setLayoutManager(layoutManager);
        Bundle bundle=this.getArguments();
        postId= bundle.getString("postid");
        Log.d("posttag", "onCreateView: "+postId);
        commentss = new ArrayList<>();
        loadComments();
        commentAdapter = new CommentAdapter(getActivity(), commentss);
        recyclerViewcomment.setAdapter(commentAdapter);


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
}