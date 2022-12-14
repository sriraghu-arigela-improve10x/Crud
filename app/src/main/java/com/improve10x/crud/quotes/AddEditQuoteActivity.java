package com.improve10x.crud.quotes;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.improve10x.crud.Constants;
import com.improve10x.crud.R;
import com.improve10x.crud.api.CrudApi;
import com.improve10x.crud.api.CrudService;
import com.improve10x.crud.base.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditQuoteActivity extends BaseActivity {

    private CrudService crudService;
    private Button addBtn;
    private EditText quoteTxt;
    private EditText authorNameTxt;
    private EditText categoryTxt;
    private EditText imageUrlTxt;
    private Quote quote;
    private Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_quote_activty);
        setupViews();
        setupApi();
        Intent intent = getIntent();
        if(intent.hasExtra(Constants.KEY_QUOTE)) {
            getSupportActionBar().setTitle("Edit Quote");
            quote = (Quote) intent.getSerializableExtra(Constants.KEY_QUOTE);
            showData();
            showEditBtn();
            handleEdit();
        } else {
            getSupportActionBar().setTitle("Add Quote");
            showAddBtn();
            handleAddBtn();

        }
    }

    private void showAddBtn() {
        addBtn.setVisibility(View.VISIBLE);
        editBtn.setVisibility(View.GONE);
    }

    private void showEditBtn() {
        addBtn.setVisibility(View.GONE);
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

    private void showData() {
        quoteTxt.setText(quote.quoteText);
        authorNameTxt.setText(quote.authorName);
        categoryTxt.setText(quote.category);
        imageUrlTxt.setText(quote.imageUrl);
    }

    private void setupViews() {
        addBtn = findViewById(R.id.add_btn);
        editBtn = findViewById(R.id.edit_btn);
        quoteTxt = findViewById(R.id.quote_txt);
        authorNameTxt = findViewById(R.id.author_name_txt);
        categoryTxt = findViewById(R.id.category_txt);
        imageUrlTxt = findViewById(R.id.imageurl_txt);
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

    private void setupApi() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
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

    private Quote createQuote(String quote, String authorName, String category, String imageUrl) {
        Quote quotes = new Quote();
        quotes.quoteText = quote;
        quotes.authorName = authorName;
        quotes.category = category;
        quotes.imageUrl = imageUrl;
        return quotes;
    }
}