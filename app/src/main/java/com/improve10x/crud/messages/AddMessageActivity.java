package com.improve10x.crud.messages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.improve10x.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);
        getSupportActionBar().setTitle("Add Message");
        handleAdd();
    }

    public void handleAdd() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            EditText nameTxt = findViewById(R.id.name_txt);
            String name = nameTxt.getText().toString();
            EditText phoneNumberTxt = findViewById(R.id.phone_number_txt);
            String phoneNumber = phoneNumberTxt.getText().toString();
            EditText addMessageTxt = findViewById(R.id.add_message_txt);
            String addMessage = addMessageTxt.getText().toString();
            createMessage(name, phoneNumber, addMessage);
        });
    }

    public void createMessage(String name, String phoneNumber, String addMessage) {
        Message messages = new Message();
        messages.title = name;
        messages.phoneNumber = phoneNumber;
        messages.messageText = addMessage;

        MessagesApi messageApi = new MessagesApi();
        MessageService messageService = messageApi.createMessageService();
        Call<Message> call = messageService.createMessage(messages);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                Toast.makeText(AddMessageActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Toast.makeText(AddMessageActivity.this, "Try again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}