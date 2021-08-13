package com.example.tilsocial;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tilsocial.FeedDetail.model.MainFeedModel;
import com.example.tilsocial.FeedDetail.model.ModelPost;
import com.example.tilsocial.FeedDetail.presentor.FeedPresentor;
import com.example.tilsocial.FeedDetail.presentor.MainContract;
import com.example.tilsocial.signup.view.EditProfile;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class ProfileFragment extends Fragment implements MainContract.MainView {

    TextView bio,name,dept,desig,empid;
    String team;
    RecyclerView recyclerView;
    UserPosts userPosts;
//    List<ModelPost> posts;
    ImageView profile,editprof;
    ChipGroup chipGroup;
    Chip chip;
    ArrayList tags;
    private MainContract.presenter presenter;
    SharedPreferences sharedPreferences;
//    ProgressBar progressBar;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        name=view.findViewById(R.id.textView);
        dept=view.findViewById(R.id.textView4);
        bio=view.findViewById(R.id.bio);
        profile=view.findViewById(R.id.profile_image);
        desig=view.findViewById(R.id.desig);
        editprof=view.findViewById(R.id.imageView2);
        chipGroup = view.findViewById(R.id.chip_group);
        empid=view.findViewById(R.id.idd);

        sharedPreferences= getActivity().getSharedPreferences("details",0);
        name.setText(sharedPreferences.getString("name",""));
        dept.setText(sharedPreferences.getString("dept",""));
        bio.setText(sharedPreferences.getString("bio",""));
        desig.setText(sharedPreferences.getString("desig",""));
        empid.setText(sharedPreferences.getString("empid", ""));
        team=sharedPreferences.getString("team","");


        HashSet set = (HashSet<String>) sharedPreferences.getStringSet("inter", null);
        tags = new ArrayList(set);

        presenter = new FeedPresentor(this,new MainFeedModel());

        int empidd=Integer.parseInt(empid.getText().toString());
        Log.d("1234", "onCreateView: "+empidd);
//        presenter.requestDataFromServer(0, "recency",12345, "self",);
        recyclerView = view.findViewById(R.id.recyid);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        editprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProfile editProfile = new EditProfile();
                Bundle args = new Bundle();
                args.putString("key", empid.getText().toString());
                editProfile.setArguments(args);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.add(R.id.dashboard, editProfile);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        //Interests
//        String[] genres = {"Mobile Application Development", "Android", "iOS","System Design"};
        chipGroup.removeAllViews();
        for(int i = 0 ; i<tags.size(); i++) {
            chip = new Chip(getActivity());
            chip.setText(tags.get(i).toString());
            chip.setChipBackgroundColor(getResources().getColorStateList(R.color.color_state_chip_outline));
            chipGroup.addView(chip);
        }
        return view;
    }


    @Override
    public void setDataToRecyclerView(List<ModelPost> modelPostList) {
//        posts = new ArrayList<>();
        userPosts = new UserPosts(getActivity(), modelPostList);
        recyclerView.setAdapter(userPosts);
    }

    @Override
    public void onResponseFailure(Throwable t) {
        Toast.makeText(getActivity(),
                "Something went wrong...Error message: " + t.getMessage(),
                Toast.LENGTH_LONG).show();
    }
}