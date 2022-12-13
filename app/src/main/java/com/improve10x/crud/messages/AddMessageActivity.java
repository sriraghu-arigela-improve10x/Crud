package com.improve10x.crud.messages;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.improve10x.crud.R;
import com.improve10x.crud.api.CrudApi;
import com.improve10x.crud.api.CrudService;
import com.improve10x.crud.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMessageActivity extends BaseActivity {

    private CrudService crudService;
    private Button addBtn;
    private EditText nameTxt;
    private EditText phoneNumberTxt;
    private  EditText addMessageTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);
        Intent intent = getIntent();



        getSupportActionBar().setTitle("Add Message");
        setupViews();
        setupApiService();
        handleAdd();
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
        addBtn = findViewById(R.id.add_btn);
        nameTxt = findViewById(R.id.name_txt);
        phoneNumberTxt = findViewById(R.id.phone_number_txt);
        addMessageTxt = findViewById(R.id.add_message_txt);
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }
}