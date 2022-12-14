package com.improve10x.crud.quotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.improve10x.crud.R;
import com.improve10x.crud.base.BaseActivity;

import java.util.ArrayList;

public class QuotesActivity extends BaseActivity {
    private ArrayList<Quote> quotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);
        getSupportActionBar().setTitle("Quotes");
        setupData();
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