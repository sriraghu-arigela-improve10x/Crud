package com.improve10x.crud.templates;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.improve10x.crud.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemplatesActivity extends AppCompatActivity {

    public ArrayList<Template> templateList;
    public RecyclerView templateRv;
    public TemplatesAdapter templatesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templates);
        getSupportActionBar().setTitle("Templates");
        addTemplatesActivity();
        setData();
        templateRv();
    }

    public void deleteTemplate(Template template) {
        TemplateApi templateApi = new TemplateApi();
        TemplateService templateService = templateApi.createTemplateService();
        Call<Void> call = templateService.deleteTemplate(template.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(TemplatesActivity.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                fetchData();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(TemplatesActivity.this, "Failed to Delete", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }

    public void fetchData() {
        TemplateApi templateApi = new TemplateApi();
        TemplateService templateService = templateApi.createTemplateService();
        Call<List<Template>> call = templateService.fetchData();
        call.enqueue(new Callback<List<Template>>() {
            @Override
            public void onResponse(Call<List<Template>> call, Response<List<Template>> response) {
                List<Template> templates = response.body();
                templatesAdapter.setData(templates);
            }

            @Override
            public void onFailure(Call<List<Template>> call, Throwable t) {
                Toast.makeText(TemplatesActivity.this, "Templates", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addTemplatesActivity() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddTemplateActivity.class);
            startActivity(intent);
        });
    }

    public void templateRv() {
        templateRv = findViewById(R.id.template_rv);
        templateRv.setLayoutManager(new LinearLayoutManager(this));
        templatesAdapter = new TemplatesAdapter();
        templatesAdapter.setData(templateList);
        templatesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(Template template) {
                Toast.makeText(TemplatesActivity.this, "On Item Clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemDelete(Template template) {
                Toast.makeText(TemplatesActivity.this, "On Item Delete", Toast.LENGTH_SHORT).show();
                deleteTemplate(template);
            }

            @Override
            public void onItemEdit(Template template) {
                Toast.makeText(TemplatesActivity.this, "On Item Edit", Toast.LENGTH_SHORT).show();
            }
        });
        templateRv.setAdapter(templatesAdapter);
    }

    public void setData() {
        templateList = new ArrayList<>();

        /*Template welcomeMessage = new Template();
        welcomeMessage.message = "Hi,\nWelcome to improve 10X";
        templateList.add(welcomeMessage);

        Template callBackMessage = new Template();
        callBackMessage.message = "Hi, I,m busy. I'll call you latter";
        templateList.add(callBackMessage);*/
    }
}