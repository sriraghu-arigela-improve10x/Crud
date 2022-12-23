package com.improve10x.crud.templates;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.improve10x.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTemplateActivity extends BaseAddEditTemplateActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Template");
        handleAdd();
        showAddBtn();

    }

    private void handleAdd() {
        binding.addBtn.setOnClickListener(view -> {
            String templateMessage = binding.templateMessageTxt.getText().toString();
            Template template = createTemplate(templateMessage);
            saveTemplate(template);
        });
    }


    private void saveTemplate(Template template) {
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

    private void showAddBtn() {
       binding.addBtn.setVisibility(View.VISIBLE);
    }
}
