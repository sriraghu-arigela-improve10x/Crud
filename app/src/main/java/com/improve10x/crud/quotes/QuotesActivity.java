package com.improve10x.crud.quotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;

import com.improve10x.crud.R;
import com.improve10x.crud.api.CrudApi;
import com.improve10x.crud.api.CrudService;
import com.improve10x.crud.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuotesActivity extends BaseActivity {
    private CrudService crudService;
    private ArrayList<Quote> quotes;
    private QuotesAdapter quotesAdapter;
    private RecyclerView quoteRv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);
        getSupportActionBar().setTitle("Quotes");
        handleAdd();
        setupData();
        setupApi();
        setupQuotesRv();
        setupAdapter();
    }

    private void handleAdd() {
        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddEditQuoteActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchQuotes();
    }

    private void fetchQuotes() {
        Call<List<Quote>> call = crudService.fetchQuotes();
        call.enqueue(new Callback<List<Quote>>() {
            @Override
            public void onResponse(Call<List<Quote>> call, Response<List<Quote>> response) {
                List<Quote> quotes = response.body();
                quotesAdapter.setData(quotes);
            }

            @Override
            public void onFailure(Call<List<Quote>> call, Throwable t) {
                showToast("failed");
            }
        });
    }

    private void setupApi() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void setupAdapter() {
        quotesAdapter = new QuotesAdapter();
        quotesAdapter.setData(quotes);
        quoteRv.setAdapter(quotesAdapter);
    }

    private void setupQuotesRv() {
        quoteRv = findViewById(R.id.quote_rv);
        quoteRv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupData() {
        quotes = new ArrayList<>();

       /* Quote quoteTxt = new Quote();
        quoteTxt.quoteText = "Your FUTURE is created by what you do TODAY";
        quoteTxt.authorName = "";
        quoteTxt.category = "career";
        quoteTxt.imageUrl = "https://www.kochiesbusinessbuilders.com.au/wp-content/uploads/2022/02/motivational-quote.jpg";
        quotes.add(quoteTxt);

        Quote quoteTxt1 = new Quote();
        quoteTxt1.quoteText = "No matter how many mistakes\nyou make or how slow your progress,\nyou are still way ahead of\neveryone who is't trying";
        quoteTxt1.authorName = "Tony Robbins";
        quoteTxt1.category = "progress";
        quoteTxt1.imageUrl = "https://emilysquotes.files.wordpress.com/2014/03/emilysquotes-com-mistakes-progress-slow-trying-tony-robbins-inspirational-motivational.jpg?w=788";
        quotes.add(quoteTxt1);*/
    }
}