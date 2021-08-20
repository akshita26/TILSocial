package com.example.tilsocial.FeedDetail.presentor;

import android.content.Context;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.tilsocial.FeedDetail.model.FeedContent;
import com.example.tilsocial.FeedDetail.model.ModelPost;
import com.example.tilsocial.FeedDetail.model.TagDetails;
import com.example.tilsocial.signup.model.SpinnerDetails;

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
    public void gettagg() {
        getFeedList.gettagsdetails(this);
    }

    @Override
    public void setnewtagss(Integer empidinterger, List<String> interestList, Context context) {
        Log.e("HomeActivityprp", "onResponse: " + interestList.toString());
        getFeedList.settaggdata(this,empidinterger,interestList,context);
    }

    @Override
    public void onFinished(List<ModelPost> modelPostList, FeedContent feedContent) {

        if(mainView != null){
            mainView.setDataToRecyclerView(modelPostList,feedContent);
        }

    }

    @Override
    public void onFinishedtag(SpinnerDetails spinnerDetails) {

        if(mainView != null){
            mainView.settagssdata(spinnerDetails);
        }

    }

    @Override
    public void onFinishedgettag(TagDetails tagDetails, Context context) {

        if(mainView != null){
            mainView.gettingtagss(tagDetails,context);




        }


    }

    @Override
    public void onFailure(Throwable t) {

        if(mainView != null){
            mainView.onResponseFailure(t);

        }

    }
}
