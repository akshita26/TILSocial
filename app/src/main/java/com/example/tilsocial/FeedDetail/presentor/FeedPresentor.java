package com.example.tilsocial.FeedDetail.presentor;

import android.widget.ProgressBar;

import com.example.tilsocial.FeedDetail.model.FeedContent;
import com.example.tilsocial.FeedDetail.model.ModelPost;

import java.util.List;

public class FeedPresentor implements MainContract.presenter ,MainContract.GetFeedList.OnFinishedListener{

    private MainContract.MainView mainView;
    private MainContract.GetFeedList getFeedList;

    public FeedPresentor(MainContract.MainView mainView, MainContract.GetFeedList getFeedList) {
        this.mainView = mainView;
        this.getFeedList = getFeedList;
    }

    @Override
    public void requestDataFromServer(int page, String filter, int empid, String type, ProgressBar loadingPB) {

        getFeedList.getFeedList(this,page,filter,empid,type,loadingPB);

    }

    @Override
    public void requestUserPost(int page, String sortby, int empid, String type) {
        getFeedList.getUserfeed(this,page,sortby,empid,type);
    }

    @Override
    public void onFinished(List<ModelPost> modelPostList, FeedContent feedContent) {

        if(mainView != null){
            mainView.setDataToRecyclerView(modelPostList,feedContent);
        }

    }

    @Override
    public void onFailure(Throwable t) {

        if(mainView != null){
            mainView.onResponseFailure(t);

        }

    }
}
