package com.improve10x.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MessagesActivity extends AppCompatActivity {

    public ArrayList<Message> messageList;
    public RecyclerView messageRv;
    public MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        getSupportActionBar().setTitle("Messages");
        setData();
        messageRv();
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

        Message aravind = new Message();
        aravind.title = "Aravind +91 9000540052";
        aravind.message = "Hi, Welcome to improve 10X.";
        messageList.add(aravind);

        Message ramesh = new Message();
        ramesh.title = "Ramesh +91 9999999999";
        ramesh.message = "Hi, Welcome to improve 10X.";
    }
}