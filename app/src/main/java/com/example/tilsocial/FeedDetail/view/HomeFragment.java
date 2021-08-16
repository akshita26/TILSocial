package com.example.tilsocial.FeedDetail.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tilsocial.FeedDetail.model.FeedContent;
import com.example.tilsocial.FeedDetail.model.MainFeedModel;
import com.example.tilsocial.FeedDetail.model.ModelPost;
import com.example.tilsocial.FeedDetail.presentor.FeedPresentor;
import com.example.tilsocial.FeedDetail.presentor.MainContract;
import com.example.tilsocial.MainActivity;
import com.example.tilsocial.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements MainContract.MainView {

    RecyclerView recyclerView;
    AdapterPosts adapterPosts;
    private MainContract.presenter presenter;
    private ProgressBar loadingPB;
    SharedPreferences prf;
    String empid;
    LinearLayoutManager manager;
    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;
    int pageno = 0;
    List<ModelPost> modelPosts;
    int flag = 0;
    int pagesize = 5;
    int lastpage = 1;
    Boolean isloading = false;
    Boolean islastpage = false;
    int totalpages ;

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
    public void onCreateOptionsMenu( Menu menu,  MenuInflater inflater) {
        inflater.inflate(R.menu.my_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

          View view =  inflater.inflate(R.layout.fragment_home, container, false);
          presenter = new FeedPresentor(this,new MainFeedModel());
          loadingPB = view.findViewById(R.id.preogressbar);
          recyclerView = view.findViewById(R.id.postrecyclerview);
          modelPosts = new ArrayList<>();
          loadfeeddata();
          manager = new LinearLayoutManager(getActivity());
          recyclerView.setLayoutManager(manager);
          adapterPosts = new AdapterPosts(getActivity(),modelPosts);
          recyclerView.setAdapter(adapterPosts);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                    isScrolling = true;
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = manager.getChildCount();
                totalItems = manager.getItemCount();
                scrollOutItems = manager.findFirstVisibleItemPosition();
                Log.e("HomeActivityfeed12323", "onResponse: " +   "curr" + currentItems  + "/" + totalItems + "/"  + scrollOutItems);

                if(!isloading && !islastpage&& isScrolling)
                {
                    isScrolling =false;
                    if((currentItems + scrollOutItems >= totalItems) && scrollOutItems >=0 && totalItems>=pagesize&& pageno!=totalpages)
                    {
                        pageno++;
                        loadfeeddata();
                    }
                }

            }
        });

        return view;
    }

    private void loadfeeddata() {

        Log.e("HomeActivityfeed13", "onResponse: " +  pageno);
        //Toast.makeText(getActivity(), "pagg" + pageno, Toast.LENGTH_SHORT).show();
        presenter.requestDataFromServer(pageno, "recency", 123456, "feed",loadingPB);

    }

    private void loadfeeddatatrending() {

        Log.e("HomeActivityfeed13", "onResponse: " +  pageno);
        //Toast.makeText(getActivity(), "pagg" + pageno, Toast.LENGTH_SHORT).show();
        presenter.requestDataFromServer(pageno, "trending", 123456, "feed",loadingPB);

    }

    @Override
    public void setDataToRecyclerView(List<ModelPost> modelPostListt, FeedContent feedContent) {
        totalpages = feedContent.getTotalPages()-1;
        isloading = true;
        modelPosts.addAll(modelPostListt);
        adapterPosts.notifyDataSetChanged();
        isloading = false;
        Log.e("size", "onResponse: " + modelPosts.size());
        if(modelPosts.size()>0)
        {
            islastpage = modelPosts.size()<pagesize;
        }
        else
        {
            islastpage = true;
        }

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
        } else if (item.getItemId() == R.id.recent) {
            modelPosts.clear();
            pageno = 0;
            loadfeeddata();
//            adapterPosts.notifyDataSetChanged();

            Toast.makeText(getActivity(), "Recent", Toast.LENGTH_SHORT).show();
//            drawer.openDrawer(GravityCompat.END);
        } else if(item.getItemId() == R.id.Trending)
        {
            modelPosts.clear();
            pageno = 0;
            loadfeeddatatrending();
            Toast.makeText(getActivity(), "Trending", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.logoutuser)
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