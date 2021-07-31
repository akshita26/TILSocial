package com.example.tilsocial;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tilsocial.signup.view.SignUpFragment;
import com.google.android.material.textfield.TextInputEditText;


public class SigninFragment extends Fragment {

    TextView signupbtn;
    Button signinbtn;
    TextInputEditText editText;



    public SigninFragment() {
        // Required empty public constructor
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
                Intent intent = new Intent(getActivity(), DashboardActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });

        return view;
    }
}