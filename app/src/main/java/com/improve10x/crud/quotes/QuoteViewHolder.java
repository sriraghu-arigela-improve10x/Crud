package com.improve10x.crud.quotes;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;

public class QuoteViewHolder extends RecyclerView.ViewHolder {
    ImageView quoteImageBtn;
    TextView authorNameTxt;
    TextView quoteTextTxt;

    public QuoteViewHolder(@NonNull View itemView) {
        super(itemView);
        quoteTextTxt = itemView.findViewById(R.id.quote_text_txt);
        authorNameTxt = itemView.findViewById(R.id.authornname_txt);
        quoteImageBtn = itemView.findViewById(R.id.quote_image_btn);

    }
}
