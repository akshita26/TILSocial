package com.example.tilsocial.addpost.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tilsocial.R;
import com.example.tilsocial.addpost.model.AddPostModel;
import com.example.tilsocial.addpost.model.AddPostRequestParams;
import com.example.tilsocial.addpost.presenter.AddPostPresenter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.resources.TextAppearance;

import org.json.JSONException;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddPostFragment extends Fragment implements AddPostPresenter.AddPostView {
    Button post;
    ImageView btncamera, btngallery, imageView, hashtag;
    EditText title, desc;
    ChipGroup chipGroup;
    String s,simage;
    Integer count;
    Uri imageUri;
    List<String> imageList = new ArrayList<>();
    List<String> hashList = new ArrayList<>();
    AddPostPresenter addPostPresenter;

    public AddPostFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPostPresenter=new AddPostPresenter(this,new AddPostModel());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_post, container, false);
        post = view.findViewById(R.id.postupload);
//        title = view.findViewById(R.id.title);
        desc = view.findViewById(R.id.description);
        btncamera = view.findViewById(R.id.camera);
        btngallery = view.findViewById(R.id.gallery);
        imageView = view.findViewById(R.id.image);
        hashtag = view.findViewById(R.id.hashtag);
        chipGroup = view.findViewById(R.id.chip_group);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddPostRequestParams addPostRequestParams=new AddPostRequestParams();
//                addPostRequestParams.setTitle(title.getText().toString());
                addPostRequestParams.setDescription(desc.getText().toString());
                addPostRequestParams.setImage(imageList);
                addPostRequestParams.setHashtag(hashList);
                try {
                    addPostPresenter.doPost(addPostRequestParams);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                count=chipGroup.getChildCount();
                Toast.makeText(getActivity(), "Posted:", Toast.LENGTH_LONG).show();
//                title.getText().clear();
                desc.getText().clear();
                imageView.setImageDrawable(null);
                imageView.getLayoutParams().height = 0;
                imageView.getLayoutParams().width = 0;
                String a="";
                for (int i=0; i<count;i++){
                    Chip chip = (Chip) chipGroup.getChildAt(0);
                    a+=chipGroup.getChildCount()+chip.getText().toString();
                    chipGroup.removeView(chip);
                }

            }
        });

        btncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
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

        desc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                post.setEnabled(!s.toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        hashtag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linearLayout = view.findViewById(R.id.linearlayout);
                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                EditText editText = new EditText(getActivity());
                editText.setLayoutParams(p);
                linearLayout.addView(editText, 2);
                editText.setText("#");
                Selection.setSelection(editText.getText(), editText.getText().length());

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (!s.toString().startsWith("#")) {
                            editText.setText("#");
                            Selection.setSelection(editText.getText(), editText.getText().length());
                        }
                    }
                });

                editText.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if ((keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_SPACE)) {
                            try {
                                s = editText.getText().toString();
                                editText.setVisibility(View.GONE);
                                Chip chip = new Chip(getActivity());
                                chip.setText(s);
                                hashList.add(s);
                                chip.setCloseIconVisible(true);
                                chip.setTextColor(getResources().getColor(R.color.grey_60));
                                chip.setTextAppearance(R.style.TextAppearance_AppCompat_Body2);
                                chipGroup.addView(chip);
                                chip.setOnCloseIconClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        chipGroup.removeView(chip);
                                    }
                                });
                                return true;
                            }
                            catch (Exception e){
                                Toast.makeText(getActivity(), "Re--"+e, Toast.LENGTH_LONG).show();
                            }
                        }
                        return false;
                    }
                });

            }
        });

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 26) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            imageView.getLayoutParams().height = 200;
            imageView.getLayoutParams().width = 200;
            try{
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
                File mFileTemp = null;
                mFileTemp= File.createTempFile("ab"+timeStamp,".jpg",getActivity().getCacheDir());
                FileOutputStream fout;
                fout = new FileOutputStream(mFileTemp);
                bitmap.compress(Bitmap.CompressFormat.PNG, 70, fout);
                fout.flush();
                imageUri=Uri.fromFile(mFileTemp);
                simage = imageUri.toString();
                imageList.add(simage);
            }
            catch (Exception e){
                Toast.makeText(getActivity(),""+e,Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == 27) {
            imageUri = data.getData();
            simage = imageUri.toString();
            imageList.add(simage);
            Uri selectedImageUri = data.getData();
            if (null != selectedImageUri) {
                imageView.setImageURI(selectedImageUri);
                imageView.getLayoutParams().height = 200;
                imageView.getLayoutParams().width = 200;
            }
        }
    }


    @Override
    public void showError() {
        Toast.makeText(getActivity(), "Required Fields", Toast.LENGTH_SHORT).show();

    }
}