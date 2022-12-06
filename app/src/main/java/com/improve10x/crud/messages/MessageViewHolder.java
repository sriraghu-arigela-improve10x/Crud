package com.improve10x.crud.messages;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    public TextView titleTxt;
    public TextView numberTxt;
    public TextView messageTextTxt;
    //messageTextTxt
    public ImageView deleteBtn;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
        titleTxt = itemView.findViewById(R.id.title_txt);
        numberTxt = itemView.findViewById(R.id.number_txt);
        messageTextTxt = itemView.findViewById(R.id.message_txt);
        deleteBtn = itemView.findViewById(R.id.delete_btn);
    }
}
