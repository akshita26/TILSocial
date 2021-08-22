package com.example.tilsocial.comments.presenter;

import com.example.tilsocial.comments.model.CommentModel;
import com.example.tilsocial.comments.model.PostComment;

import java.util.List;

public class CommentPresenter implements MainContractComment.presenter, MainContractComment.Model.OnFinishedListener {

     MainContractComment.MainView mainView;
     MainContractComment.Model model;

    public CommentPresenter(MainContractComment.MainView mainView, MainContractComment.Model model) {
        this.mainView = mainView;
        this.model = model;
    }

    @Override
    public void requestdatafromserver(String postId) {
        model.getComments(this,postId);
    }

    @Override
    public void postcomment(PostComment postComment) {
        model.postcomment(postComment,this);
    }

    @Override
    public void onFinished(List<CommentModel> commentModelList) {
        if(mainView != null){
            mainView.setDataToRecyclerViewComment(commentModelList);
        }

    }

    @Override
    public void onFailure(Throwable t) {
        if(mainView != null){
            mainView.onResponseFailure(t);

        }
    }

    @Override
    public void OnFinishedSaveComment(CommentModel body) {
        if(mainView != null){
            mainView.SetNewComment(body);
        }
    }
}
