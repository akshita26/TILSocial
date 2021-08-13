package com.example.tilsocial.signin.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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


public class SigninFragment extends Fragment implements ModeltoPresenter.MainView {

    TextView signupbtn;
    Button signinbtn;
    TextInputEditText editText;
//    SigninPresentor signinPresentor;
    private ModeltoPresenter.presenter presenter;
    private ProgressDialog mProgress;
    SharedPreferences pref;

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
        pref = this.getActivity().getSharedPreferences("signindetails", Context.MODE_PRIVATE);
        editText=view.findViewById(R.id.edittext);
        signupbtn=view.findViewById(R.id.textView3);
        signinbtn=view.findViewById(R.id.button);

        mProgress = new ProgressDialog(getActivity());
        mProgress.setTitle("Processing...");
        mProgress.setMessage("Please wait...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpFragment SignUpFragment = new SignUpFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.your_placeholder, SignUpFragment);
                ft.commit();
            }
        });

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgress.show();
                SigninRequestParams signinRequestParams = new SigninRequestParams();
                signinRequestParams.setEmployeeid(editText.getText().toString());
                presenter.doSigninn(signinRequestParams);
                mProgress.dismiss();
            }
        });


        return view;
    }

    @Override
    public void setDataToRecyclerView(UserData userData) {
        Log.d("Userdataa", "getDataa: "+userData);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("empidd",userData.getEmpId().toString());
        editor.commit();
        Intent intent = new Intent(getActivity(), DashboardActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putString("empid",userData.getEmpId().toString());
        mBundle.putString("name", userData.getName());
        mBundle.putString("dept", userData.getDept());
        mBundle.putString("bio", userData.getBio());
        mBundle.putString("desig", userData.getDesignation());
        intent.putExtras(mBundle);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onResponseFailure(Throwable t) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void nextActivity() {

    }
}