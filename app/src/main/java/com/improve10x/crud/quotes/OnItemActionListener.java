package com.improve10x.crud.quotes;

public interface OnItemActionListener {

    void onItemClicked(Quote quote);

    void onItemDeleted(Quote quote);

    void onItemEdit(Quote quote);
}
