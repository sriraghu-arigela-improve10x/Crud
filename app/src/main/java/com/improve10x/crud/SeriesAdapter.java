package com.improve10x.crud;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesViewHolder> {
    public List<Series> seriesArrayList;

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
        Picasso.get().load(series.imageUrl).into(holder.seriesImg);
    }

    @Override
    public int getItemCount() {
        return seriesArrayList.size();
    }
}
