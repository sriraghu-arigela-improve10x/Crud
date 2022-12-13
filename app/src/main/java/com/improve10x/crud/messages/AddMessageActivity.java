package com.improve10x.crud.messages;

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

public class AddMessageActivity extends BaseActivity {

    private CrudService crudService;
    private EditText nameTxt;
    private EditText phoneNumberTxt;
    private EditText addMessageTxt;
    private Message message;
    private Button addBtn;
    private Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);
        setupViews();
        setupApiService();
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_MESSAGES)) {
            getSupportActionBar().setTitle("Edit Message");
            message = (Message) intent.getSerializableExtra(Constants.KEY_MESSAGES);
            showData();
            showEditBtn();
            handleEdit();
        } else {
            getSupportActionBar().setTitle("Add Message");
            showAddBtn();
            handleAdd();
        }
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

    private void showAddBtn() {
        addBtn.setVisibility(View.VISIBLE);
        editBtn.setVisibility(View.GONE);
    }

    private void showEditBtn() {
        addBtn.setVisibility(View.GONE);
        editBtn.setVisibility(View.VISIBLE);
    }

    private void showData() {
        nameTxt.setText(message.title);
        phoneNumberTxt.setText(message.phoneNumber);
        addMessageTxt.setText(message.messageText);
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
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

    private Message createMessage(String name, String phoneNumber, String addMessage) {
        Message message = new Message();
        message.title = name;
        message.phoneNumber = phoneNumber;
        message.messageText = addMessage;
        return message;
    }

    private void setupViews() {
        editBtn =findViewById(R.id.edit_btn);
        addBtn = findViewById(R.id.add_btn);
        nameTxt = findViewById(R.id.name_txt);
        phoneNumberTxt = findViewById(R.id.phone_number_txt);
        addMessageTxt = findViewById(R.id.add_message_txt);
    }
}