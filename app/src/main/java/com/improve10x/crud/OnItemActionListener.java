package com.improve10x.crud;

public interface OnItemActionListener {

    void onItemClicked(Message message);

    void onItemDelete(Message message);

    void onItemEdit(Message message);
}
