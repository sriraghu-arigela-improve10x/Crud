package com.improve10x.crud.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    public List<Movie> movieList;

    public OnItemActionListener onItemActionListener;

    public void setOnItemActionListener(OnItemActionListener listener) {
        onItemActionListener = listener;
    }



    public void setData(List<Movie> movies) {
        movieList = movies;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(view);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.movieNameTxt.setText(movie.name);
        if(movie.imageUrl != null && movie.imageUrl.isEmpty()== false) {
            Picasso.get().load(movie.imageUrl).into(holder.movieImg);
        }
        holder.deleteBtn.setOnClickListener(view -> {
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
