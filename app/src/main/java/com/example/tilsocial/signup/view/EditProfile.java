package com.example.tilsocial.signup.view;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tilsocial.FeedDetail.view.HomeFragment;
import com.example.tilsocial.R;
import com.example.tilsocial.signup.model.Departments;
import com.example.tilsocial.signup.model.SignUpModel;
import com.example.tilsocial.signup.model.SignupRequestParams;
import com.example.tilsocial.signup.model.SpinnerDetails;
import com.example.tilsocial.signup.presenter.MainContractSignup;
import com.example.tilsocial.signup.presenter.SignupPresentor;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class EditProfile extends Fragment implements MainContractSignup.MainView{
    ActionBar actionBar;
    Spinner department,team,designation;
    EditText namee,bioo;
    Button updatebtn;
    ChipGroup chipGroup;
    Chip chip;
    ArrayList<String> genres = new ArrayList<>();
    SpinnerDetails spinnerDetails;
    MainContractSignup.presenter presenter;
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferencessignup;
    ArrayList interestList= new ArrayList();
    String dept, desig, empid, teamm;


    public EditProfile() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SignupPresentor(this,new SignUpModel());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_edit_profile, container, false);

        department = view.findViewById(R.id.spinner4);
        team = view.findViewById(R.id.spinner5);
        designation = view.findViewById(R.id.spinner6);
        namee = view.findViewById(R.id.editTextTextPersonName);
        updatebtn = view.findViewById(R.id.buttonupdate);
        bioo = view.findViewById(R.id.editTextTextPersonName3);
        chipGroup = view.findViewById(R.id.chip_group);
        spinnerDetails =new SpinnerDetails();
        presenter.requestDataFromServerSpinner();
//        presenter.departmentSpinnerdetail();
////        presenter.TeamSpinnerDetail();
//        presenter.DesignationSpinnerDetail();

//        empid = getArguments().getString("key");
//        Log.d("EditProfId", "onCreateView: "+empid);

        sharedPreferences= getActivity().getSharedPreferences("details",0);

            namee.setText(sharedPreferences.getString("name",""));
            dept =sharedPreferences.getString("dept","");
            bioo.setText(sharedPreferences.getString("bio",""));
            desig= sharedPreferences.getString("desig","");
            empid =sharedPreferences.getString("empid", "");
            teamm=sharedPreferences.getString("team","");
            HashSet set = (HashSet<String>) sharedPreferences.getStringSet("inter", null);
            ArrayList tags = new ArrayList(set);
            Log.e("editprofilee", "onResponse133: " + tags.toString());





        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int empidd = Integer.parseInt(empid.isEmpty() ? "0":empid);
                SignupRequestParams signupRequestParams = new SignupRequestParams();
                signupRequestParams.setName(namee.getText().toString());
                signupRequestParams.setEmpId(empidd);
                signupRequestParams.setBio(bioo.getText().toString());
                signupRequestParams.setDept(department.getSelectedItem().toString());
                signupRequestParams.setTeam(team.getSelectedItem().toString());
                signupRequestParams.setDesignation(designation.getSelectedItem().toString());
                signupRequestParams.setInterests(genres);
                presenter.gotoprofile(signupRequestParams);
            }
        });
        return view;
    }

    @Override
    public void gettagsdata(List<String> tagss) {

        //Interests
        for(int i = 0 ; i<tagss.size(); i++) {

            chip = new Chip(getActivity());
            chip.setText(tagss.get(i));
            chip.setChipBackgroundColor(getResources().getColorStateList(R.color.color_state_chip_outline));
            chip.setCheckable(true);
            chipGroup.addView(chip);
        }


        Integer c = chipGroup.getChildCount();
        for (int j = 0; j < c; j++) {
            Chip chip = (Chip) chipGroup.getChildAt(j);
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (chip.isChecked()) {
                        interestList.add(chip.getText().toString());
                    } else {
                        interestList.remove(chip.getText());
                    }
                    Toast.makeText(getActivity(), "" + interestList, Toast.LENGTH_LONG).show();
                }
            });
        }

    }


    @Override
    public void shownamevalidation() {
        namee.requestFocus();
        namee.setError("FIELD CANNOT BE EMPTY");
    }

    @Override
    public void showgetemployeevalidation() {

    }

    @Override
    public void showbiovalidation() {
        bioo.requestFocus();
        bioo.setError("FIELD CANNOT BE EMPTY");
    }

    @Override
    public void showdepartmentvalidation() {
        Toast.makeText(getActivity(), "Department Required", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showteamvalidation() {
        Toast.makeText(getActivity(), "Team Required", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void designationvalidation() {
        Toast.makeText(getActivity(), "Designation Required", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showinterestvalidation() {
        Toast.makeText(getActivity(), "Please select minimum 3 interests", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void nextfragment() {
    }

    @Override
    public void nextfragmentprofile() {
        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.dashboard, homeFragment);
        ft.commit();
    }

    @Override
    public void onResponseFailure(Throwable t) {
        Toast.makeText(getActivity(),
                "Something went wrong...Error message: " + t.getMessage(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void SetSignupdata(SignupRequestParams signupRequestParams) {

    }

    @Override
    public void extractFb(String s) {

    }

    @Override
    public void getspinnerdata(HashMap<String, List<Departments>> map) {


        List<String> TeamList = new ArrayList<>();
        HashMap<String,List<String>> map2 = new HashMap<>();
        Log.e("Signuppresentor", "onResponse: " + map.size());
        for (Map.Entry<String, List<Departments>> set :
                map.entrySet()) {
            TeamList.add(set.getKey());
        }


        Log.e("Signuppresentor", "onResponse: " + TeamList.toString());

        final ArrayAdapter<String> TeamArrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinnneritem, TeamList);
        TeamArrayAdapter.setDropDownViewResource(R.layout.spinnneritem);
        team.setAdapter(TeamArrayAdapter);
        team.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                List<Departments> DepartmentList = new ArrayList();
                Log.e("Signuppresentorparent", "onResponse: " + parent.getItemAtPosition(position));
                DepartmentList = map.get(parent.getItemAtPosition(position));
                List<String> departmentlist = new ArrayList<>();
                for (int i = 0; i < DepartmentList.size(); i++) {
                    departmentlist.add(DepartmentList.get(i).getName());
                    Log.e("Signuppresentorparentt", "onResponse: " + DepartmentList.get(i).getDesignationslist().toString());
                    map2.put(DepartmentList.get(i).getName(),DepartmentList.get(i).getDesignationslist());
                }
                Log.e("Signuppresentormap2size", "onResponse: " + map2.size());

                final ArrayAdapter<String> DepartmentArrayAdapter = new ArrayAdapter<String>(
                        getActivity(), R.layout.spinnneritem, departmentlist);
                DepartmentArrayAdapter.setDropDownViewResource(R.layout.spinnneritem);
                department.setAdapter(DepartmentArrayAdapter);

                department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        final ArrayAdapter<String> DesignationArrayAdapter = new ArrayAdapter<String>(
                                getActivity(), R.layout.spinnneritem,   map2.get(parent.getItemAtPosition(position)));
                        DesignationArrayAdapter.setDropDownViewResource(R.layout.spinnneritem);
                        designation.setAdapter(DesignationArrayAdapter);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }



}