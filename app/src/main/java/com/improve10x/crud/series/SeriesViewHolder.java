package com.improve10x.crud.series;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;

public class SeriesViewHolder extends RecyclerView.ViewHolder {

    public TextView titleTxt;
    public ImageView seriesImg;
    public ImageButton deleteBtn;

    public SeriesViewHolder(@NonNull View itemView) {
        super(itemView);
        seriesImg = itemView.findViewById(R.id.series_img);
        titleTxt = itemView.findViewById(R.id.title_txt);
        deleteBtn = itemView.findViewById(R.id.delete_btn);
    }
}
