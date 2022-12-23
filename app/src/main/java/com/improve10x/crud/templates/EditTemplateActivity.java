package com.improve10x.crud.templates;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.improve10x.crud.Constants;
import com.improve10x.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditTemplateActivity extends BaseAddEditTemplateActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if(intent.hasExtra(Constants.KEY_TEMPLATE)) {
            getSupportActionBar().setTitle("Edit Template");
            template = (Template) intent.getSerializableExtra(Constants.KEY_TEMPLATE);
            showData();
            showEditBtn();
            handleEdit();
        }
    }

    private void showData() {
        binding.templateMessageTxt.setText(template.messageText);
    }

    private void showEditBtn() {
        binding.editBtn.setVisibility(View.VISIBLE);
    }

    private void handleEdit() {
        binding.editBtn.setOnClickListener(view -> {
            String templateMessage = binding.templateMessageTxt.getText().toString();
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
}
