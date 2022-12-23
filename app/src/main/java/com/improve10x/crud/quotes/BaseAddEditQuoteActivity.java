package com.improve10x.crud.quotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.improve10x.crud.Constants;
import com.improve10x.crud.R;
import com.improve10x.crud.api.CrudApi;
import com.improve10x.crud.api.CrudService;
import com.improve10x.crud.base.BaseActivity;
import com.improve10x.crud.databinding.ActivityAddEditQuoteActivtyBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseAddEditQuoteActivity extends BaseActivity {
    protected ActivityAddEditQuoteActivtyBinding binding;
    protected CrudService crudService;
    protected Quote quote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditQuoteActivtyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupApi();
    }

    private void setupApi() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    protected Quote createQuote(String quote, String authorName, String category, String imageUrl) {
        Quote quotes = new Quote();
        quotes.quoteText = quote;
        quotes.authorName = authorName;
        quotes.category = category;
        quotes.imageUrl = imageUrl;
        return quotes;
    }
}