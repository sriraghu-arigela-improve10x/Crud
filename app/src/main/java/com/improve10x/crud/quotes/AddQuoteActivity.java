package com.improve10x.crud.quotes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.improve10x.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddQuoteActivity extends BaseAddEditQuoteActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Quote");
        showAddBtn();
        handleAddBtn();
    }

    private void showAddBtn() {
        binding.addBtn.setVisibility(View.VISIBLE);
    }

    private void handleAddBtn() {
        binding.addBtn.setOnClickListener(view -> {
            String quotes = binding.quoteTxt.getText().toString();
            String authorName = binding.authorNameTxt.getText().toString();
            String category = binding.categoryTxt.getText().toString();
            String imageUrl = binding.imageurlTxt.getText().toString();
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
}
