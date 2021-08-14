package com.example.tilsocial.FeedDetail.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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
import com.example.tilsocial.MainActivity;
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
    private ProgressBar loadingPB;
    SharedPreferences prf;
    String empid;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        prf = this.getActivity().getSharedPreferences("details", Context.MODE_PRIVATE);
        empid = prf.getString("empid",null);
//        Toast.makeText(getActivity(), "employeeidis" + empid, Toast.LENGTH_SHORT).show();

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
        loadingPB = view.findViewById(R.id.idPBLoading);
        presenter = new FeedPresentor(this,new MainFeedModel());
        presenter.requestDataFromServer(0, "recency", 123456, "feed",loadingPB);
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
                presenter.requestDataFromServer(0, "recency", 123457, "feed",loadingPB);
            }
        });
        trendingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.requestDataFromServer(0, "trending", 123456, "feed",loadingPB);
            }
        });
        return view;
    }

    @Override
    public void setDataToRecyclerView(List<ModelPost> modelPostList) {

        adapterPosts = new AdapterPosts(getActivity());
        adapterPosts.addtopost(modelPostList);
        recyclerView.setAdapter(adapterPosts);

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
        } else if (item.getItemId() == R.id.action_filter) {
            drawer.openDrawer(GravityCompat.END);
        } else if (item.getItemId() == R.id.logoutuser)
        {
            SharedPreferences.Editor editor = prf.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();

            Toast.makeText(getActivity(), "Logout Done", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


}