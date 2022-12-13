package com.improve10x.crud.templates;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.improve10x.crud.Constants;
import com.improve10x.crud.R;
import com.improve10x.crud.api.CrudApi;
import com.improve10x.crud.api.CrudService;
import com.improve10x.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemplatesActivity extends BaseActivity {

    private CrudService crudService;
    private ArrayList<Template> templateList;
    private RecyclerView templateRv;
    private TemplatesAdapter templatesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templates);
        getSupportActionBar().setTitle("Templates");
        handleAdd();
        setupApiService();
        log("onCreate");
        setupData();
        setupTemplatesRv();
        setupTemplatesAdapter();
    }

    private void setupTemplatesAdapter() {
        templatesAdapter = new TemplatesAdapter();
        templatesAdapter.setData(templateList);
        templatesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(Template template) {
                Intent intent = new Intent(TemplatesActivity.this, AddTemplateActivity.class);
                intent.putExtra(Constants.KEY_TEMPLATE, template);
                startActivity(intent);
               // showToast("On Item Clicked");
            }

            @Override
            public void onItemDelete(Template template) {
                showToast("On Item Delete");
                deleteTemplate(template);
            }

            @Override
            public void onItemEdit(Template template) {
                showToast("On Item Edit");
            }
        });
        templateRv.setAdapter(templatesAdapter);
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void deleteTemplate(Template template) {
        Call<Void> call = crudService.deleteTemplate(template.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully Deleted");
                fetchTemplates();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to Delete");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onResume");
        fetchTemplates();
    }

    private void fetchTemplates() {
        Call<List<Template>> call = crudService.fetchTemplates();
        call.enqueue(new Callback<List<Template>>() {
            @Override
            public void onResponse(Call<List<Template>> call, Response<List<Template>> response) {
                List<Template> templates = response.body();
                templatesAdapter.setData(templates);
            }

            @Override
            public void onFailure(Call<List<Template>> call, Throwable t) {
                showToast("Failed");
            }
        });
    }

    private void handleAdd() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddTemplateActivity.class);
            startActivity(intent);
        });
    }

    private void setupTemplatesRv() {
        templateRv = findViewById(R.id.template_rv);
        templateRv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupData() {
        templateList = new ArrayList<>();

        /*Template welcomeMessage = new Template();
        welcomeMessage.message = "Hi,\nWelcome to improve 10X";
        templateList.add(welcomeMessage);

        Template callBackMessage = new Template();
        callBackMessage.message = "Hi, I,m busy. I'll call you latter";
        templateList.add(callBackMessage);*/
    }
}