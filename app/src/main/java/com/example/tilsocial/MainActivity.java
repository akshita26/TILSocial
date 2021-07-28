package com.example.tilsocial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        siggnuppfragment siggnuppfragment = new siggnuppfragment();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.your_placeholder, siggnuppfragment);

        ft.commit();


    }
}