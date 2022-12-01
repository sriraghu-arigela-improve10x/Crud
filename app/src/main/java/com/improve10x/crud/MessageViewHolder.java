package com.improve10x.crud;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    public TextView titleTxt;
    public TextView numberTxt;
    public TextView messageTxt;
    public ImageView deleteBtn;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
        titleTxt = itemView.findViewById(R.id.title_txt);
        numberTxt = itemView.findViewById(R.id.number_txt);
        messageTxt = itemView.findViewById(R.id.message_txt);
        deleteBtn = itemView.findViewById(R.id.delete_btn);
    }
}
