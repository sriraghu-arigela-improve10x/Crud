package com.improve10x.crud.messages;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;
import com.improve10x.crud.databinding.MessageItemBinding;

public class MessageViewHolder extends RecyclerView.ViewHolder {
     MessageItemBinding binding;

    public MessageViewHolder(MessageItemBinding messageItemBinding) {
        super(messageItemBinding.getRoot());
        binding = messageItemBinding;
    }
}
