package com.improve10x.crud.templates;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;
import com.improve10x.crud.databinding.TemplateItemBinding;

public class TemplateViewHolder extends RecyclerView.ViewHolder {
    TemplateItemBinding binding;

    public TemplateViewHolder(TemplateItemBinding templateItemBinding) {
        super(templateItemBinding.getRoot());
        binding = templateItemBinding;
    }
}
