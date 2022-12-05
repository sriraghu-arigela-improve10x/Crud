package com.improve10x.crud.movies;

public interface OnItemActionListener {

     void onItemClicked(Movie movie);

     void onItemDeleted(Movie movie);

     void onItemEdit(Movie movie);
}
