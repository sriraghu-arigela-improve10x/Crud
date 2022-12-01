package com.improve10x.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class TemplatesActivity extends AppCompatActivity {

    public ArrayList<Template> templateList;
    public RecyclerView templateRv;
    public TemplatesAdapter templatesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templates);
        getSupportActionBar().setTitle("Templates");
        setData();
        templateRv();
    }

    public void templateRv() {
        templateRv = findViewById(R.id.template_rv);
        templateRv.setLayoutManager(new LinearLayoutManager(this));
        templatesAdapter = new TemplatesAdapter();
        templatesAdapter.setData(templateList);
        templateRv.setAdapter(templatesAdapter);
    }

    public void setData() {
        templateList = new ArrayList<>();

        Template welcomeMessage = new Template();
        welcomeMessage.message = "Hi,\nWelcome to improve 10X";
        templateList.add(welcomeMessage);

        Template callBackMessage = new Template();
        callBackMessage.message = "Hi, I,m busy. I'll call you latter";
        templateList.add(callBackMessage);
    }
}