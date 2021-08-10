package com.example.tilsocial.FeedDetail.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tilsocial.FeedDetail.model.MainFeedModel;
import com.example.tilsocial.FeedDetail.model.ModelPost;
import com.example.tilsocial.FeedDetail.presentor.FeedPresentor;
import com.example.tilsocial.FeedDetail.presentor.MainContract;
import com.example.tilsocial.R;

import java.util.List;


public class HomeFragment extends Fragment implements MainContract.MainView {

    RecyclerView recyclerView;
    List<ModelPost> posts;
    AdapterPosts adapterPosts;
    Spinner feedspinner;
    private MainContract.presenter presenter;
    ActionBar actionBar;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.my_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        presenter = new FeedPresentor(this,new MainFeedModel());
        presenter.requestDataFromServer("recent");
        recyclerView = view.findViewById(R.id.postrecyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        feedspinner = view.findViewById(R.id.spinnersort);

        String[] sortlist = {"Recent","Trending"} ;
        ArrayAdapter spinnersort = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,sortlist);
        spinnersort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        feedspinner.setAdapter(spinnersort);
        feedspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                if(position == 0)
                {
                    presenter.requestDataFromServer("recent");
                }
                else
                {
                    presenter.requestDataFromServer("trending");
                }


            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }




    @Override
    public void setDataToRecyclerView(List<ModelPost> ModalPostList) {

        adapterPosts = new AdapterPosts(getActivity(), ModalPostList);
        recyclerView.setAdapter(adapterPosts);

        //Log.e("HomeActivityfeed", "onResponse: " +  ModalPostList.get(0).getImgurl());

    }

    @Override
    public void onResponseFailure(Throwable t) {
        Toast.makeText(getActivity(),
                "Something went wrong...Error message: " + t.getMessage(),
                Toast.LENGTH_LONG).show();

    }
}