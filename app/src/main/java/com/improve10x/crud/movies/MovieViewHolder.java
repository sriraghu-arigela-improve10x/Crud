package com.improve10x.crud.movies;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    ImageView movieImg;
    TextView movieNameTxt;
    ImageButton deleteBtn;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        movieImg = itemView.findViewById(R.id.movie_img);
        movieNameTxt = itemView.findViewById(R.id.movie_name_txt);
        deleteBtn = itemView.findViewById(R.id.delete_btn);
    }
}
