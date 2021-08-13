package com.example.tilsocial;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.tilsocial.FeedDetail.view.HomeFragment;
import com.example.tilsocial.addpost.view.AddPostFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {


    ActionBar actionBar;
    BottomNavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        actionBar = getSupportActionBar();

        navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(selectedListener);
        actionBar.setTitle("Home");

        HomeFragment fragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.dashboard, fragment, "");
        fragmentTransaction.commit();

    }



    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            switch (menuItem.getItemId()) {

                case R.id.nav_home:
                    actionBar.setTitle("Home");
                    HomeFragment fragment = new HomeFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.dashboard, fragment, "");
                    fragmentTransaction.commit();
                    return true;

                case R.id.nav_addpost:
                    actionBar.setTitle("Add Post");
                    AddPostFragment fragment1 = new AddPostFragment();
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.dashboard, fragment1);
                    fragmentTransaction1.setReorderingAllowed(true);
                    fragmentTransaction1.commit();

                    return true;

                case R.id.nav_profile:
                    actionBar.setTitle("Profile");
//                    String empid = getIntent().getStringExtra("empid");
//                    String name = getIntent().getStringExtra("name");
//                    String bio = getIntent().getStringExtra("bio");
//                    String dept = getIntent().getStringExtra("dept");
//                    String desig = getIntent().getStringExtra("desig");
//                    Bundle bundle = new Bundle();
//                    bundle.putString("empid",empid);
//                    bundle.putString("name",name);
//                    bundle.putString("dept",dept);
//                    bundle.putString("bio",bio);
//                    bundle.putString("desig",desig);
                    ProfileFragment fragment2 = new ProfileFragment();
//                    fragment2.setArguments(bundle);
                    FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.dashboard, fragment2, "");
                    fragmentTransaction2.commit();
                    return true;

            }
            return false;
        }
    };

}