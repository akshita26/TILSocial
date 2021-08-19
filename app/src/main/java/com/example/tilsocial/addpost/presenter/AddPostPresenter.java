package com.example.tilsocial.addpost.presenter;

import android.app.ProgressDialog;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.tilsocial.addpost.model.AddPostModel;
import com.example.tilsocial.addpost.model.AddPostRequestParams;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;

import java.util.UUID;

public class AddPostPresenter {
    AddPostModel addPostModel;
    AddPostView addPostView;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference = storage.getReference();
    String pathUri;

    public AddPostPresenter(AddPostView addPostView, AddPostModel addPostModel) {
        this.addPostModel = addPostModel;
        this.addPostView = addPostView;
    }

    public void doPost(AddPostRequestParams addPostRequestParams) throws JSONException {
        if (addPostRequestParams.getContent().length() < 200) {
            addPostModel.doPost(addPostRequestParams);

        } else {
            addPostView.showError();
        }
    }

    public void uploadFb(FragmentActivity context, Uri filePath) {
        if (filePath != null) {
            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            String path = UUID.randomUUID().toString();
            StorageReference ref = storageReference.child("Feed/"+path);
            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast.makeText(context, "Image Uploaded to FB!!",
                                            Toast.LENGTH_SHORT).show();
                                    pathUri="https://firebasestorage.googleapis.com/v0/b/til-social-22075.appspot.com/o/Feed%2F"+path+"?alt=media";
                                    addPostView.extractFb(pathUri);
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast.makeText(context, "Failed Upload to FB" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {

                        // Progress Listener for loading
                        // percentage on the dialog box
                        @Override
                        public void onProgress(
                                UploadTask.TaskSnapshot taskSnapshot) {
                            double progress
                                    = (100.0
                                    * taskSnapshot.getBytesTransferred()
                                    / taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage(
                                    "Uploaded "
                                            + (int) progress + "%");
                        }
                    });

        }
    }

    public interface AddPostView {
        void showError();
        void extractFb(String s);
    }
}
