package com.improve10x.crud.templates;

import android.content.Intent;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_template);
        Intent intent = getIntent();
        template = (Template) intent.getSerializableExtra(Constants.KEY_TEMPLATE);
        if(intent.hasExtra(Constants.KEY_TEMPLATE)) {
            getSupportActionBar().setTitle("Edit Template");
        } else {
            getSupportActionBar().setTitle("Add Template");
        }
        getSupportActionBar().setTitle("Add Template");
        handleAdd();
        setupAPiService();
    }

    private void handleAdd() {
        addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            templateMessageTxt = findViewById(R.id.template_message_txt);
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