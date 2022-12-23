package com.improve10x.crud.quotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;
import com.improve10x.crud.databinding.ActivityQuotesBinding;
import com.improve10x.crud.databinding.QuoteItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuoteViewHolder> {

    private List<Quote> quotes;

    private OnItemActionListener onItemActionListener;

    void setOnItemActionListener(OnItemActionListener listener) {
        onItemActionListener = listener;
    }

    void setData(List<Quote> quoteList) {
        quotes = quoteList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        QuoteItemBinding binding = QuoteItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        QuoteViewHolder quoteViewHolder = new QuoteViewHolder(binding);
        return quoteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder holder, int position) {
        Quote quote = quotes.get(position);
        holder.binding.authornameTxt.setText(quote.authorName);
        holder.binding.quoteTextTxt.setText(quote.quoteText);
        if (quote.imageUrl != null && quote.imageUrl.isEmpty() == false) {
            Picasso.get().load(quote.imageUrl).into(holder.binding.quoteImageBtn);
        }

        holder.binding.deleteBtn.setOnClickListener(view -> {
            onItemActionListener.onItemDeleted(quote);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onItemClicked(quote);
        });
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }
}
