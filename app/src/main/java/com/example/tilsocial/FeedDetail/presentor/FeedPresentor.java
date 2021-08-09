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
    public void requestDataFromServer() {

        getFeedList.getFeedList(this);

    }

    @Override
    public void onFinished(List<ModelPost> ModalPostList) {

        if(mainView != null){
            mainView.setDataToRecyclerView(ModalPostList);
        }

    }

    @Override
    public void onFailure(Throwable t) {

        if(mainView != null){
            mainView.onResponseFailure(t);

        }

    }
}
