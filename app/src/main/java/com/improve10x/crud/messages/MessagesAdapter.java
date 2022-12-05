package com.improve10x.crud.messages;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    public List<Message> messageList;
    public OnItemActionListener onItemActionListener;

    public void setData(List<Message> messages) {
        messageList = messages;
        notifyDataSetChanged();
    }

    public void setOnItemActionListener(OnItemActionListener listener) {
        onItemActionListener = listener;
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
        Message message = messageList.get(position);
        holder.titleTxt.setText(message.title);
        holder.numberTxt.setText(message.phoneNumber);
        holder.messageTxt.setText(message.messageTextTxt);
        holder.deleteBtn.setOnClickListener(view -> {
            onItemActionListener.onItemDelete(message);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onItemClicked(message);
        });
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
