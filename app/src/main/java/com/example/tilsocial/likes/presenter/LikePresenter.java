package com.example.tilsocial.likes.presenter;

import com.example.tilsocial.FeedDetail.view.AdapterPosts;
import com.example.tilsocial.likes.model.LikeModel;
import com.example.tilsocial.likes.model.PostLike;
import com.example.tilsocial.likes.view.LikeView;

public class LikePresenter implements MainContractLike.presenter{
    LikeModel likeModel;
    LikeView likeView=new LikeView(this);
    public LikePresenter(LikeModel likeModel) {
        this.likeModel=likeModel;
    }

    @Override
    public void postlike(PostLike postLike) {
        likeModel.postlike(postLike);
    }

    @Override
    public void likeresponse(PostLike postLike) {
        likeView.likeresponse(postLike);
    }
}
