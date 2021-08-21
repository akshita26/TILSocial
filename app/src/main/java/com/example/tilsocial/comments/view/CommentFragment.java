package com.example.tilsocial.comments.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tilsocial.FeedDetail.view.AdapterPosts;
import com.example.tilsocial.DashboardActivity;
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
    ImageView sendcomment,commentimg;
    EditText newcomment;
    SharedPreferences sharedPreferences;
    LinearLayout nocomment;
    String commentcnt;
    int commnetposition;
    AdapterPosts adapterPosts;
    ActionBar actionBar;

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

//        actionBar = ((DashboardActivity)getActivity()).getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

       View view = inflater.inflate(R.layout.fragment_comment, container, false);
        presenter = new CommentPresenter(this,new ModelComment());
        sendcomment=view.findViewById(R.id.sendcomment);
        newcomment=view.findViewById(R.id.editTextTextPersonName4);
        nocomment = view.findViewById(R.id.nocommentt);
        commentimg=view.findViewById(R.id.commentimge);
        recyclerViewcomment = view.findViewById(R.id.commentrecyclerview);
        recyclerViewcomment.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewcomment.setLayoutManager(layoutManager);
        Bundle bundle=this.getArguments();
        postId= bundle.getString("postid");
        commentcnt = bundle.getString("comments");
        commnetposition = bundle.getInt("commentposition");
        sharedPreferences= getActivity().getSharedPreferences("details",0);
        empId= sharedPreferences.getString("empid", "");
        Glide.with(getActivity()).load(sharedPreferences.getString("imgurl",""))
                .error(R.drawable.ic_error_outline)
                .into(commentimg);

        Log.d("posttag", "onCreateView: "+postId);
        commentss = new ArrayList<>();
        loadComments();
        commentAdapter = new CommentAdapter(getActivity(), commentss);
        recyclerViewcomment.setAdapter(commentAdapter);
        Log.d("postemp", "onClick: "+postId+" "+empId);


        sendcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostComment postComment=new PostComment();
                postComment.setComment(newcomment.getText().toString());
                postComment.setPostId(postId);
                Log.d("postemp", "onClick: "+postId+" "+empId);
                int empid = Integer.parseInt(empId);
                postComment.setEmpId(empid);
                presenter.postcomment(postComment);
//                loadComments();
//                commentss.clear();
                newcomment.setText("");
            }
        });
        return view;
    }


    private void loadComments() {
        presenter.requestdatafromserver(postId);

    }

    @Override
    public void setDataToRecyclerViewComment(List<CommentModel> commentModelList) {
        if(commentModelList.size()==0)
        {
            nocomment.setVisibility(View.VISIBLE);
        }
        else
        {
            Log.d("position", "onClick: "+commentcnt+" "+commnetposition);

            //Log.d("positioncontext", "onClick: "+ getActivity());
            nocomment.setVisibility(View.GONE);
            commentss.addAll(commentModelList);
            commentAdapter.notifyDataSetChanged();


        }




//        Log.d("TAG", "setDataToRecyclerViewComment: "+commentModelList);
    }

    @Override
    public void onResponseFailure(Throwable t) {

    }

    @Override
    public void SetNewComment(CommentModel body) {
        Log.d("TAG", "SetNewComment: "+body.toString());
        nocomment.setVisibility(View.GONE);
        commentss.add(body);
        commentAdapter.notifyDataSetChanged();
    }
}