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
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
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
    Spinner feedspinner;
    private MainContract.presenter presenter;
    private DrawerLayout drawer;
    private ProgressBar loadingPB;
    SharedPreferences prf;
    String empid;
    LinearLayoutManager manager;
    Boolean isScrolling = false;
    Boolean islastpage = false;
    int currentItems, totalItems, scrollOutItems;
    int pageno = 0;
    List<ModelPost> mypost ;
    List<ModelPost> modelPosts;
    int flag = 0;
    int pagesize = 5;
    int lastpage = 1;

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
          loadingPB = view.findViewById(R.id.idPBLoading);
          mypost = new ArrayList<>();
          presenter = new FeedPresentor(this,new MainFeedModel());
          recyclerView = view.findViewById(R.id.postrecyclerview);
          manager = new LinearLayoutManager(getActivity());
          recyclerView.setLayoutManager(manager);
//          recyclerView.setAdapter(adapterPosts);



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

                if(isScrolling && (currentItems + scrollOutItems >= totalItems) && scrollOutItems>=0&&totalItems>pagesize&&pageno!=lastpage )
                {
                        pageno = pageno + 1 ;
                   //
                        Log.e("HomeActivityfeed13", "onResponse: " +  pageno);
                        presenter.requestDataFromServer(pageno, "recency", 123456, "feed",loadingPB);

                }
            }
        });
        presenter.requestDataFromServer(pageno, "recency", 123456, "feed",loadingPB);
        return view;
    }

    @Override
    public void setDataToRecyclerView(List<ModelPost> modelPostListt, FeedContent feedContent) {


        if(flag == 0)
        {
            loadingPB.setVisibility(View.VISIBLE);
            adapterPosts = new AdapterPosts(getActivity(),modelPosts);
            mypost.addAll(modelPostListt);
            Log.e("myposttflag0", "onResponse: " +   mypost.size() );
            adapterPosts.addtopost(mypost);
            recyclerView.setAdapter(adapterPosts);
            flag = 1;
        }
        else
        {

            mypost.addAll(modelPostListt);
            Log.e("myposttflag1", "onResponse: " +   mypost.size() );
            adapterPosts.addtopost(mypost);
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