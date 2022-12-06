package com.improve10x.crud.templates;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.improve10x.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTemplateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_template);
        getSupportActionBar().setTitle("Add Template");
        handleAdd();
    }

    public void handleAdd() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            EditText templateMessageTxt = findViewById(R.id.template_message_txt);
            String templateMessage = templateMessageTxt.getText().toString();
            createTemplate(templateMessage);
        });
    }

    public void createTemplate(String templateMessage) {
        Template template = new Template();
        template.messageText = templateMessage;

        TemplatesApi templateApi = new TemplatesApi();
        TemplateService templateService = templateApi.createTemplateService();
        Call<Template> call = templateService.createTemplate(template);
        call.enqueue(new Callback<Template>() {
            @Override
            public void onResponse(Call<Template> call, Response<Template> response) {
                Toast.makeText(AddTemplateActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Template> call, Throwable t) {
                Toast.makeText(AddTemplateActivity.this, "Try again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}