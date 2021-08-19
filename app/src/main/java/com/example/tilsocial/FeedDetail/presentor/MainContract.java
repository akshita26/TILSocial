package com.example.tilsocial.FeedDetail.presentor;

import android.content.Context;
import android.widget.ProgressBar;

import com.example.tilsocial.FeedDetail.model.FeedContent;
import com.example.tilsocial.FeedDetail.model.ModelPost;
import com.example.tilsocial.FeedDetail.model.TagDetails;
import com.example.tilsocial.signup.model.SpinnerDetails;

import java.util.List;

public interface MainContract {

    interface presenter{
        void requestDataFromServer(int page, String sortby, int empid, String type, ProgressBar loadingPB);
        void requestUserPost(int page, String sortby, int empid, String type);

        void gettagg();

        void setnewtagss(Integer empidinterger, List<String> interestList, Context context);
    }

    interface MainView {

        void setDataToRecyclerView(List<ModelPost> modelPostList,FeedContent feedContent);

        void onResponseFailure(Throwable t);

        void settagssdata(SpinnerDetails spinnerDetails);

        void gettingtagss(TagDetails tagDetails, Context context);


    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface GetFeedList {

       

        interface OnFinishedListener {
            void onFinished(List<ModelPost> modelPostList, FeedContent feedContent);
            void onFinishedtag(SpinnerDetails spinnerDetails);
            void onFinishedgettag(TagDetails tagDetails, Context context);
            void onFailure(Throwable t);
        }
        void gettagsdetails(OnFinishedListener onFinishedtag);
        void getFeedList(OnFinishedListener onFinishedListener, int page, String filter, int empid, String recent, ProgressBar loadingPB);
        void getUserfeed(OnFinishedListener onFinishedListener, int page, String filter, int empid, String recent);
        void settaggdata(OnFinishedListener onFinishedgettag, Integer empidinterger, List<String> interestList, Context context);
    }



}
