package com.improve10x.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesActivity extends AppCompatActivity {

    public ArrayList<Message> messageList;
    public RecyclerView messageRv;
    public MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        getSupportActionBar().setTitle("Messages");
        addMessageActivity();
        setData();
        messageRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchTask();
    }

    public void fetchTask() {
        MessageApi messageApi = new MessageApi();
        MessageService messageService = messageApi.createMessageService();
        Call<List<Message>> call = messageService.fetchTask();
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                List<Message> messages = response.body();
                messageAdapter.setData(messages);
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Toast.makeText(MessagesActivity.this, "Message", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addMessageActivity() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddMessageActivity.class);
            startActivity(intent);
        });
    }

    public void messageRv() {
        messageRv = findViewById(R.id.message_rv);
        messageRv.setLayoutManager(new LinearLayoutManager(this));
        messageAdapter = new MessageAdapter();
        messageAdapter.setData(messageList);
        messageRv.setAdapter(messageAdapter);
    }

    public void setData() {
        messageList = new ArrayList<>();

       /* Message aravind = new Message();
        aravind.title = "Aravind ";
        aravind.phoneNumber = "+91 9000540052";
        aravind.message = "Hi, Welcome to improve 10X.";
        messageList.add(aravind);

        Message ramesh = new Message();
        ramesh.title = "Ramesh";
        ramesh.phoneNumber = "+91 9999999999";
        ramesh.message = "Hi, Welcome to improve 10X.";
        messageList.add(ramesh);*/
    }
}