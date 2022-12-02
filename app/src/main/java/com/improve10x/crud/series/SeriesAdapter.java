package com.improve10x.crud.series;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesViewHolder> {
    public List<Series> seriesArrayList;

    public OnItemActionListener onItemActionListener;

    public void setOnItemActionListener(OnItemActionListener listener) {
        onItemActionListener = listener;
    }

    public void setData(List<Series> series1) {
        seriesArrayList = series1;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.series_item, parent, false);
        SeriesViewHolder seriesViewHolder = new SeriesViewHolder(view);
        return seriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesViewHolder holder, int position) {
        Series series = seriesArrayList.get(position);
        holder.titleTxt.setText(series.title);
        if(series.imageUrl!= null&& series.imageUrl.isEmpty()== false) {
            Picasso.get().load(series.imageUrl).into(holder.seriesImg);
        }
        holder.deleteBtn.setOnClickListener(view -> {
            onItemActionListener.onItemDelete(series);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onItemClicked(series);
        });
    }

    @Override
    public int getItemCount() {
        return seriesArrayList.size();
    }
}
