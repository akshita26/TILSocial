package com.example.tilsocial.FeedDetail.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
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
    AdapterPosts adapterPosts;
    Spinner feedspinner;
    private MainContract.presenter presenter;
    private DrawerLayout drawer;
    RadioButton recentbtn;
    RadioButton trendingbtn;


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
        presenter.requestDataFromServer(0, "recency", 123456, "feed");
        recyclerView = view.findViewById(R.id.postrecyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        drawer = view.findViewById(R.id.drawer_layout);
        recentbtn = view.findViewById(R.id.order_by_1);
        trendingbtn = view.findViewById(R.id.order_by_2);



        recentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.requestDataFromServer(0, "recency", 123457, "feed");
            }
        });
        trendingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.requestDataFromServer(0, "trending", 123456, "feed");
            }
        });


        return view;
    }

    @Override
    public void setDataToRecyclerView(List<ModelPost> modelPostList) {

        adapterPosts = new AdapterPosts(getActivity(), modelPostList);
        recyclerView.setAdapter(adapterPosts);

        //Log.e("HomeActivityfeed", "onResponse: " +  ModalPostList.get(0).getImgurl());

    }


    @Override
    public void onResponseFailure(Throwable t) {
        Toast.makeText(getActivity(),
                "Something went wrong...Error message: " + t.getMessage(),
                Toast.LENGTH_LONG).show();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            requireActivity().onBackPressed();
        } else if (item.getItemId() == R.id.action_filter ) {
            drawer.openDrawer(GravityCompat.END);
        } else {
            Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


}