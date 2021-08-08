package com.example.tilsocial.signin.view;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.tilsocial.signin.presentor.SigninPresentor;
import com.example.tilsocial.signup.model.SignupRequestParams;
import com.example.tilsocial.signup.view.SignUpFragment;
import com.google.android.material.textfield.TextInputEditText;


public class SigninFragment extends Fragment implements SigninPresentor.SigninView {

    TextView signupbtn;
    Button signinbtn;
    TextInputEditText editText;
    SigninPresentor signinPresentor;



    public SigninFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signinPresentor=new SigninPresentor(this, new SignInModel());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_signin, container, false);

        editText=view.findViewById(R.id.edittext);
        signupbtn=view.findViewById(R.id.textView3);
        signinbtn=view.findViewById(R.id.button);

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
                SigninRequestParams signinRequestParams = new SigninRequestParams();
                signinRequestParams.setEmployeeid(editText.getText().toString());
            int res=signinPresentor.doSignin(signinRequestParams,getActivity());
            if (res==1){
                Intent intent = new Intent(getActivity(), DashboardActivity.class);
                startActivity(intent);
                getActivity().finish();
            } else {
                Toast.makeText(getActivity(), "Incorrect ID", Toast.LENGTH_SHORT).show();
            }
//                Intent intent = new Intent(getActivity(), DashboardActivity.class);
//                startActivity(intent);
//                getActivity().finish();

            }
        });

        return view;
    }

    @Override
    public void showError() {

    }
}