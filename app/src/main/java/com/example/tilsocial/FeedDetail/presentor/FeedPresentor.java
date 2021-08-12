package com.example.tilsocial.FeedDetail.presentor;

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
    public void requestDataFromServer(int page, String filter, int empid, String type) {

        getFeedList.getFeedList(this,page,filter,empid,type);

    }

    @Override
    public void onFinished(List<ModelPost> modelPostList) {

        if(mainView != null){
            mainView.setDataToRecyclerView(modelPostList);
        }

    }

    @Override
    public void onFailure(Throwable t) {

        if(mainView != null){
            mainView.onResponseFailure(t);

        }

    }
}
