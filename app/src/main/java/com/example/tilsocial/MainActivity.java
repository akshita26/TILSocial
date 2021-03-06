package com.example.tilsocial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.tilsocial.signin.view.SigninFragment;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences=getSharedPreferences("details",0);
        String empid=sharedPreferences.getString("empid", "");

            if(sharedPreferences.contains("empid")){
                Intent intent=new Intent(MainActivity.this,DashboardActivity.class);
                startActivity(intent);
                finish();
            }
            else {
                SigninFragment signinfragment = new SigninFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.your_placeholder, signinfragment);
                ft.commit();
            }

    }

}