package com.example.tilsocial.addpost.presenter;

import com.example.tilsocial.addpost.model.AddPostModel;
import com.example.tilsocial.addpost.model.AddPostRequestParams;

import org.json.JSONException;

public class AddPostPresenter {
    AddPostModel addPostModel;
    AddPostView addPostView;

    public AddPostPresenter(AddPostView addPostView, AddPostModel addPostModel) {
        this.addPostModel = addPostModel;
        this.addPostView = addPostView;
    }

    public void doPost(AddPostRequestParams addPostRequestParams) throws JSONException {
        if (addPostRequestParams.getDescription().length() < 200) {
            addPostModel.doPost(addPostRequestParams);

        } else {
            addPostView.showError();
        }
    }

    public interface AddPostView {
        void showError();
    }
}
