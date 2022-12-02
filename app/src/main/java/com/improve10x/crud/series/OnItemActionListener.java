package com.improve10x.crud.series;

public interface OnItemActionListener {

    void onItemClicked(Series series);

    void onItemDelete(Series series);

    void onItemEdit(Series series);
}
