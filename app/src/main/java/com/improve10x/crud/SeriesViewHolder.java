package com.improve10x.crud;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SeriesViewHolder extends RecyclerView.ViewHolder {

    public TextView titleTxt;
    public ImageView seriesImg;

    public SeriesViewHolder(@NonNull View itemView) {
        super(itemView);
        seriesImg = itemView.findViewById(R.id.series_img);
        titleTxt = itemView.findViewById(R.id.title_txt);
    }
}
