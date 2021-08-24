package com.example.tilsocial.likes.view;

import com.example.tilsocial.FeedDetail.view.AdapterPosts;
import com.example.tilsocial.likes.model.PostLike;
import com.example.tilsocial.likes.presenter.LikePresenter;
import com.example.tilsocial.likes.presenter.MainContractLike;

public class LikeView implements MainContractLike.MainView {
    LikePresenter likePresenter;
    public LikeView(LikePresenter likePresenter) {
        this.likePresenter=likePresenter;
    }

    @Override
    public void postlike(PostLike postLike) {
        likePresenter.postlike(postLike);
    }

    @Override
    public void likeresponse(PostLike postLike) {

    }

    @Override
    public void onResponseFailure(Throwable t) {

    }

}
