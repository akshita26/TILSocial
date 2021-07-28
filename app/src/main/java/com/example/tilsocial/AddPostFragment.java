package com.example.tilsocial;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;

public class AddPostFragment extends Fragment {
    Button btn;
    ImageView btncamera, btngallery, imageView, hashtag;
    EditText title, desc;
    Chip tag1,tag2,tag3,tag4,tag5,tag6;

    public AddPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_post, container, false);
        btn = view.findViewById(R.id.postupload);
        title = view.findViewById(R.id.title);
        desc = view.findViewById(R.id.description);
        btncamera = view.findViewById(R.id.camera);
        btngallery = view.findViewById(R.id.gallery);
        imageView = view.findViewById(R.id.image);
        hashtag=view.findViewById(R.id.hashtag);
        tag1=view.findViewById(R.id.tag1);
        tag2=view.findViewById(R.id.tag2);
        tag3=view.findViewById(R.id.tag3);
        tag4=view.findViewById(R.id.tag4);
        tag5=view.findViewById(R.id.tag5);
        tag6=view.findViewById(R.id.tag6);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Posted", Toast.LENGTH_LONG).show();
                title.getText().clear();
                desc.getText().clear();
                imageView.setImageDrawable(null);
                imageView.getLayoutParams().height = 0;
                imageView.getLayoutParams().width = 0;
                tag1.setVisibility(View.GONE);
                tag2.setVisibility(View.GONE);
                tag3.setVisibility(View.GONE);
                tag4.setVisibility(View.GONE);
                tag5.setVisibility(View.GONE);
                tag6.setVisibility(View.GONE);

            }
        });

        btncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 26);
            }
        });

        btngallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 27);
            }
        });

        hashtag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tag1.setVisibility(View.VISIBLE);
                tag2.setVisibility(View.VISIBLE);
                tag3.setVisibility(View.VISIBLE);
                tag4.setVisibility(View.VISIBLE);
                tag5.setVisibility(View.VISIBLE);
                tag6.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==26){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            imageView.getLayoutParams().height = 200;
            imageView.getLayoutParams().width = 200;
        }
        else if (requestCode==27){
            Uri selectedImageUri = data.getData();
            if (null != selectedImageUri) {
                imageView.setImageURI(selectedImageUri);
                imageView.getLayoutParams().height = 200;
                imageView.getLayoutParams().width = 200;
            }
        }
    }


}