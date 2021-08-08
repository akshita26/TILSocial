package com.example.tilsocial.FeedDetail.presentor;

import com.example.tilsocial.FeedDetail.model.FeedPostModel;
import com.example.tilsocial.signup.model.SignupRequestParams;

public class FeedPresentor {

          FeedPostModel feedPostModel;
          FeedPostView feedPostView;

    public FeedPresentor(FeedPostModel feedPostModel, FeedPostView feedPostView) {
        this.feedPostModel = feedPostModel;
        this.feedPostView = feedPostView;
    }

    public void GetFeedPost()    {


        feedPostModel.GetFeedPost();



    }

    public  interface FeedPostView
    {
        void showError () ;

    }

}
