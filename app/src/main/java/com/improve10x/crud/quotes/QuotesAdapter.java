package com.improve10x.crud.quotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuoteViewHolder> {

    private List<Quote> quotes;

    void setData(List<Quote> quoteList) {
        quotes = quoteList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quote_item, parent, false);
        QuoteViewHolder quoteViewHolder = new QuoteViewHolder(view);
        return quoteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder holder, int position) {
        Quote quote = quotes.get(position);
        holder.authorNameTxt.setText(quote.authorName);
        holder.quoteTextTxt.setText(quote.quoteText);
        Picasso.get().load(quote.imageUrl).into(holder.quoteImageBtn);
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }
}
