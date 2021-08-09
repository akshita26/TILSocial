package com.example.tilsocial.FeedDetail.presentor;

import com.example.tilsocial.FeedDetail.model.ModelPost;

import java.util.List;

public interface MainContract {

    interface presenter{

        void requestDataFromServer();


    }

    interface MainView {



        void setDataToRecyclerView(List<ModelPost> ModalPostList);


        void onResponseFailure(Throwable t);
    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface GetFeedList {

        interface OnFinishedListener {
            void onFinished(List<ModelPost> ModalPostList);
            void onFailure(Throwable t);
        }

        void getFeedList(OnFinishedListener onFinishedListener);
    }




}
