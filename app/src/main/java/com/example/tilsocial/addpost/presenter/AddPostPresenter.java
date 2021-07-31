package com.example.tilsocial.addpost.presenter;

import com.example.tilsocial.addpost.model.AddPostModel;
import com.example.tilsocial.addpost.model.AddPostRequestParams;

public class AddPostPresenter {
    AddPostModel addPostModel;
    AddPostView addPostView;

    public AddPostPresenter(AddPostView addPostView, AddPostModel addPostModel) {
        this.addPostModel = addPostModel;
        this.addPostView = addPostView;
    }

    public void doPost(AddPostRequestParams addPostRequestParams) {
        if (addPostRequestParams.getTitle().length() > 0) {
            addPostModel.doPost(addPostRequestParams);

        } else {
            addPostView.showError();
        }
    }

    public interface AddPostView {
        void showError();
    }
}
