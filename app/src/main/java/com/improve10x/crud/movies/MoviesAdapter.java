package com.improve10x.crud.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;
import com.improve10x.crud.databinding.MovieItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<Movie> movieList;

    private OnItemActionListener onItemActionListener;

    void setOnItemActionListener(OnItemActionListener listener) {
        onItemActionListener = listener;
    }

    void setData(List<Movie> movies) {
        movieList = movies;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieItemBinding binding = MovieItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(binding);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.binding.setMovie(movie);
        holder.binding.deleteBtn.setOnClickListener(view -> {
            onItemActionListener.onItemDeleted(movie);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onItemClicked(movie);
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
