package com.example.tilsocial.FeedDetail.view;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tilsocial.DashboardActivity;
import com.example.tilsocial.FeedDetail.model.FeedContent;
import com.example.tilsocial.FeedDetail.model.MainFeedModel;
import com.example.tilsocial.FeedDetail.model.ModelPost;
import com.example.tilsocial.FeedDetail.model.TagDetails;
import com.example.tilsocial.FeedDetail.presentor.FeedPresentor;
import com.example.tilsocial.FeedDetail.presentor.MainContract;
import com.example.tilsocial.MainActivity;
import com.example.tilsocial.R;
import com.example.tilsocial.signup.model.SpinnerDetails;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class HomeFragment extends Fragment implements MainContract.MainView {

    RecyclerView recyclerView;
    AdapterPosts adapterPosts;
    private MainContract.presenter presenter;
    private ProgressBar loadingPB;
    SharedPreferences prf;
    Integer empidinterger;
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
    LinearLayout nopost;
    List<String > taggs = new ArrayList<>();
    SharedPreferences sharedPreferences;
    ArrayList intersett = new ArrayList();
    Context context;
    Parcelable recyclerViewState;
    HomeFragment fragment1;
    boolean trendingpageon = false;






    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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

          prf = getActivity().getSharedPreferences("details", 0);
          context = getActivity();
          empid = prf.getString("empid","");
          HashSet set = (HashSet<String>) prf.getStringSet("inter", null);
          ArrayList tags = new ArrayList(set);
          intersett.addAll(tags);
          Log.e("editprofileehome", "onResponse133: " + tags.toString());
          empidinterger = Integer.parseInt(empid);
          presenter = new FeedPresentor(this,new MainFeedModel());
          presenter.gettagg();
          loadingPB = view.findViewById(R.id.preogressbar);
          nopost = view.findViewById(R.id.noresultt);
          recyclerView = view.findViewById(R.id.postrecyclerview);
          modelPosts = new ArrayList<>();
          loadfeeddata();
          manager = new LinearLayoutManager(getActivity());
          recyclerView.setLayoutManager(manager);
          adapterPosts = new AdapterPosts(getActivity(),modelPosts,taggs, intersett,empidinterger);
          recyclerView.setAdapter(adapterPosts);
          taggs.clear();


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

                    if(trendingpageon == true)
                    {
                        if((currentItems + scrollOutItems >= totalItems) && scrollOutItems >=0 && totalItems>=pagesize&&pageno!=totalpages)
                        {

                            pageno++;
                            loadfeeddatatrending();
                        }

                    }
                    else
                    {
                        if((currentItems + scrollOutItems >= totalItems) && scrollOutItems >=0 && totalItems>=pagesize&&pageno!=totalpages)
                        {
                            //
                            pageno++;
                            loadfeeddata();
                            //loadfeeddatatrending();
                        }

                    }



                }

            }
        });

        return view;
    }

    public void loadfeeddata() {


        if(pageno == 0)
        {
         loadingPB.setVisibility(View.VISIBLE);
        }

        Log.e("HomeActivityfeed13", "onResponse: " +  pageno);
        //Toast.makeText(getActivity(), "pagg" + pageno, Toast.LENGTH_SHORT).show();
        presenter.requestDataFromServer(pageno, "recency", empidinterger, "feed",loadingPB);

    }

    private void loadfeeddatatrending() {

        Log.e("HomeActivityfeed13", "onResponse: " +  pageno);
        //Toast.makeText(getActivity(), "pagg" + pageno, Toast.LENGTH_SHORT).show();
        presenter.requestDataFromServer(pageno, "trending", empidinterger, "feed",loadingPB);


    }

    @Override
    public void setDataToRecyclerView(List<ModelPost> modelPostListt, FeedContent feedContent) {
        Log.e("HomeActivityfeedlistt", "onResponse: " +  modelPostListt.toString());
        Log.e("HomeActivityfeedpagenoo", "onResponse: " +  feedContent.getTotalPages());
        if(modelPostListt.size() == 0 && pageno == 0)
        {
            nopost.setVisibility(View.VISIBLE);
        }
        else
        {


            totalpages = feedContent.getTotalPages()-1;
            loadingPB.setVisibility(View.GONE);
            isloading = true;


            modelPosts.addAll(modelPostListt);
            adapterPosts.notifyDataSetChanged();
            //recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
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


    }
    @Override
    public void onResponseFailure(Throwable t) {
        Toast.makeText(getActivity(),
                "Something went wrong...Error message: " + t.getMessage(),
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void settagssdata(SpinnerDetails spinnerDetails) {

        Log.e("HomeActivityfeedtag", "onResponse: " + spinnerDetails.getTagslist());


        taggs.addAll(spinnerDetails.getTagslist());




    }

    @Override
    public void gettingtagss(TagDetails tagDetails, Context context) {

        Log.e("contextcheck", "onResponse: " + context);
        prf = context.getSharedPreferences("details", 0);
        SharedPreferences.Editor editor = prf.edit();
        HashSet<String> set = new HashSet(tagDetails.getTagglist());
        editor.putStringSet("inter", set);
        editor.commit();

        HomeFragment fragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.dashboard, fragment, "");
        fragmentTransaction.commit();



        Log.e("HomeActivityfeedtag464", "onResponse: " + tagDetails.getTagglist());

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
            trendingpageon = true;
            loadfeeddatatrending();
            Toast.makeText(getActivity(), "Trending", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void callsaveinterset(List<String> interestList, int empidd, Context context) {
        presenter = new FeedPresentor(this,new MainFeedModel());
        presenter.setnewtagss(empidd,interestList,context);
    }

    @Override
    public void onResume(){
        super.onResume();
        ((DashboardActivity) getActivity()).setActionBarTitle("TIL Social");
    }
}


