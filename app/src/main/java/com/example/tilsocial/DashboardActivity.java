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
//        actionBar.setDisplayHomeAsUpEnabled(true);

        navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(selectedListener);
        actionBar.setTitle("Home");

        HomeFragment fragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.dashboard, fragment, "");
        fragmentTransaction.commit();
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(navigationView.getSelectedItemId()==R.id.nav_home){
//            super.onOptionsItemSelected(item);
//            finish();
//        }
//        else{
//            navigationView.setSelectedItemId(R.id.nav_home);
//        }
//        return super.onOptionsItemSelected(item);
//    }

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
                    actionBar.setTitle("Users");
                    ProfileFragment fragment2 = new ProfileFragment();
                    FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.dashboard, fragment2, "");
                    fragmentTransaction2.commit();
                    return true;

            }
            return false;
        }
    };


//    @Override
//    public void onBackPressed() {
//        if(navigationView.getSelectedItemId()==R.id.nav_home){
//            super.onBackPressed();
//            finish();
//        }
//        else{
//            navigationView.setSelectedItemId(R.id.nav_home);
//        }
//    }
}