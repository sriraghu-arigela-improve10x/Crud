package com.improve10x.crud.messages;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.improve10x.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMessageActivity extends BaseAddEditMessageActivity{

    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Message");
        setupViews();
        showAddBtn();
        handleAdd();
    }

    private void setupViews() {
        addBtn = findViewById(R.id.add_btn);
    }

    private void showAddBtn() {
        addBtn.setVisibility(View.VISIBLE);
    }

    private void handleAdd() {
        addBtn.setOnClickListener(view -> {
            String name = nameTxt.getText().toString();
            String phoneNumber = phoneNumberTxt.getText().toString();
            String addMessage = addMessageTxt.getText().toString();
            Message message = createMessage(name, phoneNumber, addMessage);
            saveMessage(message);
        });
    }

    private void saveMessage(Message messages) {
        Call<Message> call = crudService.createMessage(messages);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                showToast("Successfully");
                finish();
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                showToast("Try again");
            }
        });
    }
}
