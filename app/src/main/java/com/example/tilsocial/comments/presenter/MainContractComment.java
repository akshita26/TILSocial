package com.example.tilsocial.comments.presenter;

import com.example.tilsocial.comments.model.CommentModel;
import com.example.tilsocial.comments.model.PostComment;
import com.example.tilsocial.comments.model.PostCommentResponse;

import java.util.List;

public interface MainContractComment {
    interface presenter{
        void requestdatafromserver(String postId);
        void postcomment(PostComment postComment);
    }

    interface MainView {
        void setDataToRecyclerViewComment(List<CommentModel> commentModelList);

        void onResponseFailure(Throwable t);

        void SetNewComment(CommentModel body);
    }


    interface Model {

        void postcomment(PostComment postComment, OnFinishedListener onFinishedListener);

        interface OnFinishedListener {
            void onFinished(List<CommentModel> commentModelList);
            void onFailure(Throwable t);

            void OnFinishedSaveComment(CommentModel body);
        }
        void getComments(OnFinishedListener onFinishedListener, String postId);

    }

}
