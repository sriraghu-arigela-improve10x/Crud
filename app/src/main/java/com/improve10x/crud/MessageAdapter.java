package com.improve10x.crud;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    public ArrayList<Message> messageArrayList;

    public void setData(ArrayList<Message> messages) {
        messageArrayList = messages;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        MessageViewHolder messageViewHolder = new MessageViewHolder(view);
        return messageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messageArrayList.get(position);
        holder.titleTxt.setText(message.title);
        holder.messageTxt.setText(message.message);
    }

    @Override
    public int getItemCount() {
        return messageArrayList.size();
    }
}
