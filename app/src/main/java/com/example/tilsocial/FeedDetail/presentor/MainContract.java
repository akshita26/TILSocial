package com.example.tilsocial.FeedDetail.presentor;

import android.widget.ProgressBar;

import com.example.tilsocial.FeedDetail.model.ModelPost;

import java.util.List;

public interface MainContract {

    interface presenter{

        void requestDataFromServer(int page, String sortby, int empid, String type, ProgressBar loadingPB);



    }

    interface MainView {



        void setDataToRecyclerView(List<ModelPost> modelPostList);


        void onResponseFailure(Throwable t);
    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface GetFeedList {

        interface OnFinishedListener {
            void onFinished(List<ModelPost> modelPostList);
            void onFailure(Throwable t);
        }

        void getFeedList(OnFinishedListener onFinishedListener, int page, String filter, int empid, String recent, ProgressBar loadingPB);
    }



}
