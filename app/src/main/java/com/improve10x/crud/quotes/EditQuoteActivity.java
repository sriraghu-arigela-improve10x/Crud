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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if(intent.hasExtra(Constants.KEY_QUOTE)) {
            getSupportActionBar().setTitle("Edit Quote");
            quote = (Quote) intent.getSerializableExtra(Constants.KEY_QUOTE);
            showData();
            showEditBtn();
            handleEdit();
        }
    }

    private void showData() {
        binding.quoteTxt.setText(quote.quoteText);
        binding.authorNameTxt.setText(quote.authorName);
        binding.categoryTxt.setText(quote.category);
        binding.imageurlTxt.setText(quote.imageUrl);
    }

    private void showEditBtn() {
        binding.editBtn.setVisibility(View.VISIBLE);
    }

    private void handleEdit() {
        binding.editBtn.setOnClickListener(view -> {
            String quotes = binding.quoteTxt.getText().toString();
            String authorName = binding.authorNameTxt.getText().toString();
            String category = binding.categoryTxt.getText().toString();
            String imageUrl = binding.imageurlTxt.getText().toString();
            Quote updatedQuote = createQuote(quotes, authorName, category, imageUrl);
            updateQuote(quote.id, updatedQuote);
        });
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
