package com.example.tilsocial.likes.presenter;

import com.example.tilsocial.comments.model.CommentModel;
import com.example.tilsocial.comments.model.PostComment;
import com.example.tilsocial.comments.presenter.MainContractComment;
import com.example.tilsocial.likes.model.PostLike;

import java.util.List;

public interface MainContractLike {
    interface presenter {
        void postlike(PostLike postLike);

        void likeresponse(PostLike postLike);
    }

    interface MainView {
        void onResponseFailure(Throwable t);
        void postlike(PostLike postLike);

        void likeresponse(PostLike postLike);
    }

    interface Model {
        void postlike(PostLike postLike);
    }
}
