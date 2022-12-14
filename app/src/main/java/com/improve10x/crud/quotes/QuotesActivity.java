package com.improve10x.crud.quotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.improve10x.crud.R;
import com.improve10x.crud.api.CrudService;
import com.improve10x.crud.base.BaseActivity;

import java.util.ArrayList;

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
        setupData();
        setupQuotesRv();
        setupAdapter();
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

        Quote quoteTxt = new Quote();
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
        quotes.add(quoteTxt1);
    }
}