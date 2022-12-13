package com.improve10x.crud.templates;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.improve10x.crud.Constants;
import com.improve10x.crud.R;
import com.improve10x.crud.api.CrudApi;
import com.improve10x.crud.api.CrudService;
import com.improve10x.crud.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTemplateActivity extends BaseActivity {

    private CrudService crudService;
    private Button addBtn;
    private EditText templateMessageTxt;
    private Template template;
    private Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_template);
        setupViews();
        setupAPiService();
        Intent intent = getIntent();
        if(intent.hasExtra(Constants.KEY_TEMPLATE)) {
            getSupportActionBar().setTitle("Edit Template");
            template = (Template) intent.getSerializableExtra(Constants.KEY_TEMPLATE);
            showData();
            showEditBtn();
            handleEdit();
        } else {
            getSupportActionBar().setTitle("Add Template");
            handleAdd();
            showAddBtn();
        }
    }

    private void handleEdit() {
        editBtn.setOnClickListener(view -> {
            String templateMessage = templateMessageTxt.getText().toString();
            Template updatedTemplate = createTemplate(templateMessage);
            updateTemplate(template.id, updatedTemplate);
        });
    }

    private void updateTemplate(String id, Template updatedTemplate) {
        Call<Void> call = crudService.updateTemplate(id,updatedTemplate);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Success");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed");
            }
        });
    }

    private void showEditBtn() {
        addBtn.setVisibility(View.GONE);
        editBtn.setVisibility(View.VISIBLE);
    }

    private void showAddBtn() {
        addBtn.setVisibility(View.VISIBLE);
        editBtn.setVisibility(View.GONE);
    }

    private void showData() {
        templateMessageTxt.setText(template.messageText);
    }

    private void setupViews() {
        editBtn = findViewById(R.id.edit_btn);
        addBtn = findViewById(R.id.add_btn);
        templateMessageTxt = findViewById(R.id.template_message_txt);
    }

    private void handleAdd() {
        addBtn.setOnClickListener(view -> {
            String templateMessage = templateMessageTxt.getText().toString();
            Template template = createTemplate(templateMessage);
            saveMessage(template);
        });
    }

    private void setupAPiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private Template createTemplate(String templateMessage) {
        Template template = new Template();
        template.messageText = templateMessage;
        return template;
    }

    private void saveMessage(Template template) {
        Call<Template> call = crudService.createTemplate(template);
        call.enqueue(new Callback<Template>() {
            @Override
            public void onResponse(Call<Template> call, Response<Template> response) {
                showToast("Successfully");
                finish();
            }

            @Override
            public void onFailure(Call<Template> call, Throwable t) {
                showToast("Try again");
            }
        });
    }
}