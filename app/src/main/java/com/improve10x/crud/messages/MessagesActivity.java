package com.improve10x.crud.messages;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.improve10x.crud.Constants;
import com.improve10x.crud.R;
import com.improve10x.crud.api.CrudApi;
import com.improve10x.crud.api.CrudService;
import com.improve10x.crud.base.BaseActivity;
import com.improve10x.crud.databinding.ActivityMessagesBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesActivity extends BaseActivity {
    private ActivityMessagesBinding binding;
    private CrudService crudService;
    private ArrayList<Message> messageList;
    private MessagesAdapter messagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMessagesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Messages");
        handleAdd();
        setupData();
        setupApiService();
        log("onCreate");
        setupMessagesRv();
        setupMessagesAdapter();
    }

    private void setupMessagesAdapter() {
        messagesAdapter = new MessagesAdapter();
        messagesAdapter.setData(messageList);
        messagesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(Message message) {
                Intent intent = new Intent(MessagesActivity.this, EditMessageActivity.class);
                intent.putExtra(Constants.KEY_MESSAGES, message);
                startActivity(intent);
                //showToast("On Item Clicked");
            }

            @Override
            public void onItemDelete(Message message) {
                showToast("On Item Delete");
                deleteMessage(message);
            }

            @Override
            public void onItemEdit(Message message) {
                showToast("On Item Edit");
            }
        });
        binding.messagesRv.setAdapter(messagesAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        log("onResume");
        fetchMessages();
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void fetchMessages() {
        Call<List<Message>> call = crudService.fetchMessages();
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                List<Message> messages = response.body();
                messagesAdapter.setData(messages);
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                showToast("Message");
            }
        });
    }

    private void deleteMessage(Message message) {
        Call<Void> call = crudService.deleteMessage(message.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully Deleted");
                fetchMessages();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
               showToast("Fail to delete Message");
            }
        });
    }

    private void handleAdd() {
        binding.addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this,AddMessageActivity.class);
            startActivity(intent);
        });
    }

    private void setupMessagesRv() {
       binding.messagesRv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupData() {
        messageList = new ArrayList<>();

       /* Message aravind = new Message();
        aravind.title = "Aravind ";
        aravind.phoneNumber = "+91 9000540052";
        aravind.messageTextTxt = "Hi, Welcome to improve 10X.";
        messageList.add(aravind);

        Message ramesh = new Message();
        ramesh.title = "Ramesh";
        ramesh.phoneNumber = "+91 9999999999";
        ramesh.message = "Hi, Welcome to improve 10X.";
        messageList.add(ramesh);*/
    }
}