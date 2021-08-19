package com.example.tilsocial.addpost.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tilsocial.R;
import com.example.tilsocial.addpost.model.AddPostModel;
import com.example.tilsocial.addpost.model.AddPostRequestParams;
import com.example.tilsocial.addpost.presenter.AddPostPresenter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

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
    ImageView btncamera, btngallery, imageView, cancelimage;
    EditText title, desc;
    ChipGroup chipGroup, chipGroup2;
    Chip chip;
    Integer count, cinterest, isdesc = 0, isinterest = 0, i_width=0;
    Uri imageUri;
    List<String> interest;
    String[] interestList;
    List<String> imageList = new ArrayList<>();
    AddPostPresenter addPostPresenter;
    SharedPreferences sharedPreferences,preferences;
    String empid;
    Integer empidinteger;
    ArrayList tags;

    public AddPostFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPostPresenter = new AddPostPresenter(this, new AddPostModel());
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
        interest_tag = view.findViewById(R.id.spinner);
        cancelimage = view.findViewById(R.id.cancelimage);

        preferences= getActivity().getSharedPreferences("tags",0);

//        HashSet set = (HashSet<String>) preferences.getStringSet("interests", null);
//        Log.d("Interestsss", "onCreateView: "+set);
//        tags = new ArrayList(set);
//        Log.d("tagsss", "onCreateView: "+tags);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interestList=new String[]{};
                interestList=interest.toArray(interestList);
                AddPostRequestParams addPostRequestParams = new AddPostRequestParams();
//                addPostRequestParams.setTitle(title.getText().toString());
                addPostRequestParams.setContent(desc.getText().toString());
                addPostRequestParams.setImages(imageList);
                addPostRequestParams.setTags(interestList);
                sharedPreferences=getActivity().getSharedPreferences("details",0);
                empid=sharedPreferences.getString("empid", "");
                empidinteger = Integer.parseInt(empid);
                addPostRequestParams.setEmpId(empidinteger);
                try {
                    addPostPresenter.doPost(addPostRequestParams);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                count = chipGroup.getChildCount();
                Toast.makeText(getActivity(), "Posted", Toast.LENGTH_LONG).show();
//                title.getText().clear();
                desc.getText().clear();
                imageView.setImageDrawable(null);
                imageView.getLayoutParams().height = 0;
                imageView.getLayoutParams().width = 0;

                cancelimage.setImageDrawable(null);
                cancelimage.getLayoutParams().height = 0;
                cancelimage.getLayoutParams().width = 0;
                String a = "";
                for (int i = 0; i < count; i++) {
                    Chip chip = (Chip) chipGroup.getChildAt(0);
                    a += chipGroup.getChildCount() + chip.getText().toString();
                    chipGroup.removeView(chip);
                }
                post.setTextColor(getResources().getColor(R.color.white));
                post.setBackground(getActivity().getResources().getDrawable(R.drawable.button_shape));
                post.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.grey_20)));
                post.setEnabled(false);
                isdesc=0;
                isinterest=0;
                interest = new ArrayList<>();
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
                isdesc = 1;
                if (isinterest >= 1) {
                    Boolean b=!s.toString().trim().isEmpty();
                    post.setEnabled(b);
                    if(b) {
                        post.setBackground(getActivity().getResources().getDrawable(R.drawable.button_shape));
                        post.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.basic)));
                    }
                    else{
                        post.setTextColor(getResources().getColor(R.color.white));
                        post.setBackground(getActivity().getResources().getDrawable(R.drawable.button_shape));
                        post.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.grey_20)));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        Interest Tag selection
        String[] Interests = new String[]{
                "Select Interest...",
                "React",
                "Spring",
                "Python",
                "SQL",
                "Android",
                "Frontend",
                "Backend"
        };

        final List<String> InterestList = new ArrayList<>(Arrays.asList(Interests));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> TeamArrayAdapter = new ArrayAdapter<String>(
                getActivity(), R.layout.spinnneritem, Interests);
        TeamArrayAdapter.setDropDownViewResource(R.layout.spinnneritem);

        interest_tag.setAdapter(TeamArrayAdapter);

        interest = new ArrayList<>();
        interest_tag.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    if (!interest.contains(adapterView.getItemAtPosition(i).toString())) {
                        interest.add(adapterView.getItemAtPosition(i).toString());
                        isinterest += 1;
                        chip = new Chip(getActivity());
                        chip.setText(adapterView.getItemAtPosition(i).toString());
                        chip.setCloseIconVisible(true);
                        chipGroup.addView(chip);

                        interest_tag.setSelection(i);

                        cinterest = chipGroup.getChildCount();
                        for (int j = 0; j < cinterest; j++) {
                            Chip chip = (Chip) chipGroup.getChildAt(j);
                            chip.setOnCloseIconClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    chipGroup.removeView(chip);
                                    interest.remove(chip.getText());
                                    isinterest -= 1;
                                    if (isinterest == 0) {
                                        post.setTextColor(getResources().getColor(R.color.white));
                                        post.setBackground(getActivity().getResources().getDrawable(R.drawable.button_shape));
                                        post.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.grey_20)));
                                        post.setEnabled(false);
                                    }
                                }
                            });
                        }
                        if (isdesc == 1 && isinterest >= 1) {
                            post.setEnabled(true);
                            post.setBackground(getActivity().getResources().getDrawable(R.drawable.button_shape));
                            post.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.basic)));
                        }
                    }
                    else{
                        Toast.makeText(getActivity(), adapterView.getItemAtPosition(i).toString()+" already selected", Toast.LENGTH_SHORT).show();
                        interest_tag.setSelection(0);
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
        if (requestCode == 26 && resultCode != 0) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            imageView.getLayoutParams().height = 200;
            imageView.getLayoutParams().width = 200;
//            i_width = 200;
//            i_width = i_width + i_width/2;
            try {
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
                File mFileTemp = null;
                mFileTemp = File.createTempFile("ab" + timeStamp, ".jpg", getActivity().getCacheDir());
                FileOutputStream fout;
                fout = new FileOutputStream(mFileTemp);
                bitmap.compress(Bitmap.CompressFormat.PNG, 70, fout);
                fout.flush();
                imageUri = Uri.fromFile(mFileTemp);
               // simage = imageUri.toString();
                 addPostPresenter.uploadFb(getActivity(),imageUri);
//                imageList.add(simage);
                cancelimage.setVisibility(View.VISIBLE);
                cancelimage.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                cancelimage.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                cancelimage.setBackgroundResource(android.R.drawable.presence_offline);
            } catch (Exception e) {
                Toast.makeText(getActivity(), "" + e, Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == 27 && resultCode != 0) {
            imageUri = data.getData();
           // simage = imageUri.toString();
            addPostPresenter.uploadFb(getActivity(),imageUri);
//            imageList.add(simage);
            Uri selectedImageUri = data.getData();
            if (null != selectedImageUri) {
                imageView.setImageURI(selectedImageUri);
                imageView.getLayoutParams().height = 200;
                imageView.getLayoutParams().width = 200;
                i_width = 200;
                i_width = i_width + i_width/2;
                cancelimage.setVisibility(View.VISIBLE);
                cancelimage.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                cancelimage.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                cancelimage.setBackgroundResource(android.R.drawable.presence_offline);
            }
        }

        cancelimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageDrawable(null);
                imageView.getLayoutParams().height = 0;
                imageView.getLayoutParams().width = 0;
                imageList.clear();
                cancelimage.setImageDrawable(null);
                cancelimage.getLayoutParams().height = 0;
                cancelimage.getLayoutParams().width = 0;
            }
        });
    }


    @Override
    public void showError() {
        Toast.makeText(getActivity(), "Required Fields", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void extractFb(String uri) {

        Log.e("imagechecking", "onResponse: " +  uri);
        imageList.add(uri);
    }
}