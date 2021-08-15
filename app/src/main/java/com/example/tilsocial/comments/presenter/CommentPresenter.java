package com.example.tilsocial.comments.presenter;

import android.util.Log;

import com.example.tilsocial.comments.model.CommentModel;

import java.util.List;

public class CommentPresenter implements MainContractComment.presenter, MainContractComment.Model.OnFinishedListener {

    private MainContractComment.MainView mainView;
    private MainContractComment.Model model;

    public CommentPresenter(MainContractComment.MainView mainView, MainContractComment.Model model) {
        this.mainView = mainView;
        this.model = model;
    }

    @Override
    public void requestdatafromserver(String postId) {
        model.getComments(this,postId);
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
}
