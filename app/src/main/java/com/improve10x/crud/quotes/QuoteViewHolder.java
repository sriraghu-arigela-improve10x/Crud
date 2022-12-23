package com.improve10x.crud.quotes;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;
import com.improve10x.crud.databinding.QuoteItemBinding;

public class QuoteViewHolder extends RecyclerView.ViewHolder {
    QuoteItemBinding binding;

    public QuoteViewHolder(QuoteItemBinding quoteItemBinding) {
        super(quoteItemBinding.getRoot());
        binding = quoteItemBinding;
    }
}
