package com.improve10x.crud.series;

public interface OnItemActionListener {

    void onItemClicked(SeriesItem series);

    void onItemDelete(SeriesItem series);

    void onItemEdit(SeriesItem series);
}
