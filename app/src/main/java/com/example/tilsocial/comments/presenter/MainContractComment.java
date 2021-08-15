package com.example.tilsocial.comments.presenter;

import com.example.tilsocial.comments.model.CommentModel;

import java.util.List;

public interface MainContractComment {
    interface presenter{
        void requestdatafromserver(String postId);
    }

    interface MainView {
        void setDataToRecyclerViewComment(List<CommentModel> commentModelList);

        void onResponseFailure(Throwable t);
    }


    interface Model {

        interface OnFinishedListener {
            void onFinished(List<CommentModel> commentModelList);
            void onFailure(Throwable t);
        }
        void getComments(OnFinishedListener onFinishedListener, String postId);

    }

}
