package com.example.tilsocial.signin.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tilsocial.DashboardActivity;
import com.example.tilsocial.R;
import com.example.tilsocial.signin.model.SignInModel;
import com.example.tilsocial.signin.model.SigninRequestParams;
import com.example.tilsocial.signin.model.UserData;
import com.example.tilsocial.signin.presentor.ModeltoPresenter;
import com.example.tilsocial.signin.presentor.SigninPresentor;
import com.example.tilsocial.signup.view.SignUpFragment;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashSet;


public class SigninFragment extends Fragment implements ModeltoPresenter.MainView {

    TextView tv;
    Button signinbtn;
    TextInputEditText editText;
//    SigninPresentor signinPresentor;
    private ModeltoPresenter.presenter presenter;
    private ProgressDialog mProgress;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SigninFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=new SigninPresentor(this, new SignInModel());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_signin, container, false);

        editText=view.findViewById(R.id.edittext);
        signinbtn=view.findViewById(R.id.button);
        tv=view.findViewById(R.id.textview);

        mProgress = new ProgressDialog(getActivity());
        mProgress.setTitle("Processing...");
        mProgress.setMessage("Please wait...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgress.show();
                SigninRequestParams signinRequestParams = new SigninRequestParams();
                signinRequestParams.setEmployeeid(editText.getText().toString());
                presenter.doSigninn(signinRequestParams, mProgress);
            }
        });


        return view;
    }

    @Override
    public void setDataToRecyclerView(UserData userData) {
        sharedPreferences = getActivity().getSharedPreferences("details", 0);
        editor = sharedPreferences.edit();
        editor.putString("empid",userData.getEmpId().toString());
        editor.putString("name", userData.getName());
        editor.putString("dept", userData.getDept());
        editor.putString("bio", userData.getBio());
        editor.putString("desig", userData.getDesignation());
        HashSet<String> set = new HashSet(userData.getInterests());
        editor.putStringSet("inter", set);
        editor.putString("team", userData.getTeam());
        editor.putString("imgurl",userData.getImgUrl());
        Log.d("profilepicture", "setDataToRecyclerView: "+userData.getImgUrl());
        editor.commit();

        Intent intent = new Intent(getActivity(), DashboardActivity.class);
        startActivity(intent);
        getActivity().finish();

    }

    @Override
    public void onResponseFailure(Throwable t) {
        Log.e("Signin", "onResponse: " +  t.getMessage());
        tv.setText("User not exist");
        tv.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
        tv.setVisibility(View.VISIBLE);
        tv.setText("Incorrect user id");
    }

    @Override
    public void nextActivity() {
    }

    @Override
    public void noUserfound(String error) {
        Toast.makeText(getActivity(), ""+error, Toast.LENGTH_SHORT).show();
        SignUpFragment SignUpFragment = new SignUpFragment();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.your_placeholder, SignUpFragment);
        ft.commit();
    }
}