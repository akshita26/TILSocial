package com.example.tilsocial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tilsocial.databinding.ActivityInterestBinding;

public class Interest extends AppCompatActivity {
    ActivityInterestBinding binding;
    //changed


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_interest);
        ClickHandler handler;
        handler= new ClickHandler(this);
        binding.setClickHandler(handler);
    }


    public class ClickHandler {
        private Context context;

        public ClickHandler(Context context) {
            this.context = context;
        }

        public void simpleButtonOnClick(View view) {
            Toast.makeText(context, "clicked....", Toast.LENGTH_SHORT).show();
        }
    }
}