package com.example.tilsocial.signup.presenter;


import android.app.ProgressDialog;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.tilsocial.signup.model.Departments;
import com.example.tilsocial.signup.model.SignupRequestParams;
import com.example.tilsocial.signup.model.SpinnerDetails;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SignupPresentor implements MainContractSignup.presenter ,MainContractSignup.Model.OnFinishedListener {

    private MainContractSignup.MainView mainView;
    private MainContractSignup.Model model;
    private  FirebaseStorage storage = FirebaseStorage.getInstance();
    private  StorageReference storageReference= storage.getReference();
    private  String pathUri;
    HashMap<String, List<Departments>> map  = new HashMap<>();

    public SignupPresentor(MainContractSignup.MainView mainView, MainContractSignup.Model model) {
        this.mainView = mainView;
        this.model = model;
    }

    public void uploadFb(FragmentActivity context, Uri filePath) {
        if (filePath != null) {
            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            String path = UUID.randomUUID().toString();
            StorageReference ref = storageReference.child("UserProfile/"+path);
            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    progressDialog.dismiss();
                                    Toast.makeText(context, "Image Uploaded !!",
                                            Toast.LENGTH_SHORT).show();
                                    pathUri="https://firebasestorage.googleapis.com/v0/b/til-social-22075.appspot.com/o/UserProfile%2F"+path+"?alt=media&token=c1d8b478-5902-48f2-89bb-026fc7870101";
                                    mainView.extractFb(pathUri);
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Log.d("TAG232", "onFailure: "+e.getMessage());
                            Toast.makeText(context, "Failed Upload " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
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

    @Override
    public void onFinished(SpinnerDetails spinnerDetails) {

        if(mainView != null){


            for(int i=0;i<spinnerDetails.getTeamslist().size();i++)
            {
                map.put(spinnerDetails.getTeamslist().get(i).getTeamm(),spinnerDetails.getTeamslist().get(i).getDepartmentsList());
            }
            Log.e("Signuppresentor134", "onResponse: " +  map.size());
            mainView.gettagsdata(spinnerDetails.getTagslist());
            mainView.getspinnerdata(map);


        }
    }


    private boolean validateInputs(Integer employeeid, String name, String bio, String deprtment, String teamm, String desgniationn, ArrayList interests) {

        if (name.isEmpty()) {
            mainView.shownamevalidation();
            return true;
        }
        if (employeeid.toString().equals("0")) {
            mainView.showgetemployeevalidation();
            return true;
        }
        if (bio.isEmpty()) {
            mainView.showbiovalidation();
            return true;
        }
        if (deprtment.equals("Select department...")) {
            mainView.showdepartmentvalidation();
            return true;
        }
        if (teamm.equals("Select Team...")) {
            mainView.showteamvalidation();
            return true;
        }
        if (desgniationn.equals("Select Designation...")) {
            mainView.designationvalidation();
            return true;
        }
        if(interests.size()<3){
            mainView.showinterestvalidation();
            return true;
        }
        return false;

    }


    @Override
    public void dosignup(SignupRequestParams signupRequestParams) {

        if (!validateInputs(signupRequestParams.getEmpId(), signupRequestParams.getName(), signupRequestParams.getBio(), signupRequestParams.getDept(), signupRequestParams.getTeam(), signupRequestParams.getDesignation(), signupRequestParams.getInterests())) {

            model.dosignup(signupRequestParams,this);
            mainView.nextfragment();
        }

    }

    @Override
    public void gotoprofile(SignupRequestParams signupRequestParams) {
        if (!validateInputs(signupRequestParams.getEmpId(), signupRequestParams.getName(), signupRequestParams.getBio(), signupRequestParams.getDept(), signupRequestParams.getTeam(), signupRequestParams.getDesignation(), signupRequestParams.getInterests())) {

            model.dosignup(signupRequestParams,this);

//            mainView.nextfragmentprofile();
        }
    }


    @Override
    public void requestDataFromServerSpinner() {

        model.getSpinnerDetail(this);

    }

    @Override
    public void getsignupdetails(SignupRequestParams signupRequestParams) {

        if(mainView != null){
            mainView.SetSignupdata( signupRequestParams);
        }
    }




    @Override
    public void OnFinishedSignupdata(SignupRequestParams signupRequestParams) {

        if(mainView != null){
            mainView.SetSignupdata(signupRequestParams);
        }

    }

    @Override
    public void onFailure(Throwable t) {

        if(mainView != null){
            mainView.onResponseFailure(t);

        }

    }
}
