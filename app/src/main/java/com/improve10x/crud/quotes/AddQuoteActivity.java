package com.improve10x.crud.quotes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.improve10x.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddQuoteActivity extends BaseAddEditQuoteActivity {
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();
        getSupportActionBar().setTitle("Add Quote");
        showAddBtn();
        handleAddBtn();
    }

    private void showAddBtn() {
        addBtn.setVisibility(View.VISIBLE);
    }

    private void handleAddBtn() {
        addBtn.setOnClickListener(view -> {
            String quotes = quoteTxt.getText().toString();
            String authorName = authorNameTxt.getText().toString();
            String category = categoryTxt.getText().toString();
            String imageUrl = imageUrlTxt.getText().toString();
            Quote quote = createQuote(quotes, authorName, category, imageUrl);
            saveQuote(quote);
        });
    }

    private void saveQuote(Quote quote) {
        Call<Quote> call = crudService.createQuote(quote);
        call.enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(Call<Quote> call, Response<Quote> response) {
                showToast("Successfully");
                finish();
            }

            @Override
            public void onFailure(Call<Quote> call, Throwable t) {
                showToast("Try Again");
            }
        });
    }

    private void setupViews() {
        addBtn = findViewById(R.id.add_btn);
    }
}
