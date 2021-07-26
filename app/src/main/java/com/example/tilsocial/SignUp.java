package com.example.tilsocial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class SignUp extends AppCompatActivity {



    ArrayAdapter DepartmentarrayAdapter;
    ArrayList DepartmentarrayList;
    ArrayAdapter TeamarrayAdapter;
    ArrayList TeamarrayList;

    ArrayAdapter DesignationarrayAdapter;
    ArrayList DesignationarrayList;
    Spinner department;
    Spinner team;
    Spinner designation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        department = findViewById(R.id.spinner4);
        team = findViewById(R.id.spinner5);
        designation = findViewById(R.id.spinner6);

        DepartmentarrayList = new ArrayList();
        DepartmentarrayList.add("Department1");
        DepartmentarrayList.add("Department2");
        DepartmentarrayList.add("Department3");
        DepartmentarrayList.add("Department4");
        DepartmentarrayList.add("Department5");

        TeamarrayList = new ArrayList();
        TeamarrayList.add("Team1");
        TeamarrayList.add("Team2");
        TeamarrayList.add("Team3");
        TeamarrayList.add("Team4");
        TeamarrayList.add("Team5");

        DesignationarrayList = new ArrayList();
        DesignationarrayList.add("designation1");
        DesignationarrayList.add("designation2");
        DesignationarrayList.add("designation3");
        DesignationarrayList.add("designation4");
        DesignationarrayList.add("designation5");

        //department Spinner

        DepartmentarrayAdapter = new ArrayAdapter(SignUp.this, android.R.layout.simple_list_item_1,DepartmentarrayList);

        department.setAdapter(DepartmentarrayAdapter);

        //Team Spinner

        TeamarrayAdapter = new ArrayAdapter(SignUp.this, android.R.layout.simple_list_item_1,TeamarrayList);

        team.setAdapter(TeamarrayAdapter);

        //Designation

        DesignationarrayAdapter = new ArrayAdapter(SignUp.this, android.R.layout.simple_list_item_1,DesignationarrayList);

        designation.setAdapter(DesignationarrayAdapter);

    }
}