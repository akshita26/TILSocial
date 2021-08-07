package com.example.tilsocial.addpost.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddPostFragment extends Fragment implements AddPostPresenter.AddPostView {
    Button post;
    Spinner interest_tag;
    ImageView btncamera, btngallery, imageView;
    EditText title, desc;
    ChipGroup chipGroup,chipGroup2;
    Chip chip;
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
        chipGroup = view.findViewById(R.id.chip_group);
        interest_tag=view.findViewById(R.id.spinner);
        chipGroup2 = view.findViewById(R.id.chip_group2);


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
                post.setBackgroundColor(getResources().getColor(R.color.teal_600));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        Interest Tag selection
        String[] Interests = new String[]{
                "Select Interest...",
                "Interest 1",
                "Interest 2",
                "Interest 3",
                "Interest 4"
        };

        final List<String> InterestList = new ArrayList<>(Arrays.asList(Interests));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> TeamArrayAdapter = new ArrayAdapter<String>(
                getActivity(),R.layout.spinnneritem, InterestList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        TeamArrayAdapter.setDropDownViewResource(R.layout.spinnneritem);
        interest_tag.setAdapter(TeamArrayAdapter);

        ArrayList<String> interest= new ArrayList<>();
        interest_tag.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0) {
                    interest.add(adapterView.getItemAtPosition(i).toString());
                    chipGroup2.removeAllViews();
                    for(String genre : interest) {
                        chip = new Chip(getActivity());
                        chip.setText(genre);
                        chipGroup2.addView(chip);
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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