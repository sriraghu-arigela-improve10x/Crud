package com.improve10x.crud.quotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.improve10x.crud.Constants;
import com.improve10x.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditQuoteActivity extends BaseAddEditQuoteActivity{
    private Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();
        Intent intent = getIntent();
        if(intent.hasExtra(Constants.KEY_QUOTE)) {
            getSupportActionBar().setTitle("Edit Quote");
            quote = (Quote) intent.getSerializableExtra(Constants.KEY_QUOTE);
            showData();
            showEditBtn();
            handleEdit();
        }
    }

    void setupViews() {
        editBtn = findViewById(R.id.edit_btn);
    }

    private void showData() {
        quoteTxt.setText(quote.quoteText);
        authorNameTxt.setText(quote.authorName);
        categoryTxt.setText(quote.category);
        imageUrlTxt.setText(quote.imageUrl);
    }

    private void showEditBtn() {
        editBtn.setVisibility(View.VISIBLE);
    }

    private void handleEdit() {
        editBtn.setOnClickListener(view -> {
            String quotes = quoteTxt.getText().toString();
            String authorName = authorNameTxt.getText().toString();
            String category = categoryTxt.getText().toString();
            String imageUrl = imageUrlTxt.getText().toString();
            Quote updatedQuote = createQuote(quotes, authorName, category, imageUrl);
            updateQuote(quote.id, updatedQuote);
        });
    }

    private Quote createQuote(String quote, String authorName, String category, String imageUrl) {
        Quote quotes = new Quote();
        quotes.quoteText = quote;
        quotes.authorName = authorName;
        quotes.category = category;
        quotes.imageUrl = imageUrl;
        return quotes;
    }

    private void updateQuote(String id, Quote updatedQuote) {
        Call<Void> call = crudService.updatedQuote(id, updatedQuote);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed");
            }
        });
    }
}
