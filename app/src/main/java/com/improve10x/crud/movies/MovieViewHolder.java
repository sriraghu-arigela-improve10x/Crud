package com.improve10x.crud.movies;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;
import com.improve10x.crud.databinding.MessageItemBinding;
import com.improve10x.crud.databinding.MovieItemBinding;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    MovieItemBinding binding;

    public MovieViewHolder(MovieItemBinding movieItemBinding) {
        super(movieItemBinding.getRoot());
        binding = movieItemBinding;
    }
}
