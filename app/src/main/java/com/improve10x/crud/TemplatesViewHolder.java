package com.improve10x.crud;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TemplatesViewHolder extends RecyclerView.ViewHolder {

    public TextView templateTxt;

    public TemplatesViewHolder(@NonNull View itemView) {
        super(itemView);
        templateTxt = itemView.findViewById(R.id.template_txt);
    }
}
