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

public class BaseAddEditTemplateActivity extends BaseActivity {

    protected CrudService crudService;
    protected EditText templateMessageTxt;
    protected Template template;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_template);
        setupViews();
        setupAPiService();
    }

    private void setupViews() {
        templateMessageTxt = findViewById(R.id.template_message_txt);
    }

    private void setupAPiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    protected Template createTemplate(String templateMessage) {
        Template template = new Template();
        template.messageText = templateMessage;
        return template;
    }
}