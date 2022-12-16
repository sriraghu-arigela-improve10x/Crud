package com.improve10x.crud.messages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.improve10x.crud.Constants;
import com.improve10x.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMessageActivity extends BaseAddEditMessageActivity{

    private Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();
        Intent intent = getIntent();
        if(intent.hasExtra(Constants.KEY_MESSAGES)) {
            getSupportActionBar().setTitle("Edit Message");
            message = (Message) intent.getSerializableExtra(Constants.KEY_MESSAGES);
            showData();
            showEditBtn();
            handleEdit();
        }
    }

    private void setupViews() {
        editBtn = findViewById(R.id.edit_btn);
    }

    private void showData() {
        nameTxt.setText(message.title);
        phoneNumberTxt.setText(message.phoneNumber);
        addMessageTxt.setText(message.messageText);
    }

    private void showEditBtn() {
        editBtn.setVisibility(View.VISIBLE);
    }

    private void handleEdit() {
        editBtn.setOnClickListener(view -> {
            String name = nameTxt.getText().toString();
            String phoneNUmber = phoneNumberTxt.getText().toString();
            String addMessage = addMessageTxt.getText().toString();
            Message updatedMessage = createMessage(name, phoneNUmber, addMessage);
            updateMessage(message.id, updatedMessage);
        });
    }

    private void updateMessage(String id, Message updatedMessage) {
        Call<Void> call = crudService.updateMessage(id, updatedMessage);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed");
            }
        });
    }
}
