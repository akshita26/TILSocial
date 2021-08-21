package com.example.tilsocial.FeedDetail.view;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tilsocial.R;
import com.github.chrisbanes.photoview.PhotoView;

public class ImageFragment extends DialogFragment {
    ImageView imageView;
    PhotoView photoView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_image, container, false);
//        imageView=view.findViewById(R.id.imageView2);
        photoView=view.findViewById(R.id.photo_view);
        Bundle bundle=this.getArguments();
        Glide.with(getActivity()).load(bundle.getString("postimg"))
                .placeholder(R.drawable.noimageeee)
                .error(R.drawable.noimageeee)
                .into(photoView);
        return view;
    }
}