package com.improve10x.crud.quotes;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.improve10x.crud.Constants;
import com.improve10x.crud.R;
import com.improve10x.crud.api.CrudApi;
import com.improve10x.crud.api.CrudService;
import com.improve10x.crud.base.BaseActivity;
import com.improve10x.crud.databinding.ActivityQuotesBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuotesActivity extends BaseActivity {
    private ActivityQuotesBinding binding;
    private CrudService crudService;
    private ArrayList<Quote> quotes;
    private QuotesAdapter quotesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Quotes");
        handleAdd();
        setupData();
        setupApi();
        setupQuotesRv();
        setupAdapter();
    }

    private void handleAdd() {
        binding.addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddQuoteActivity.class);
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
        quotesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(Quote quote) {
                Intent intent = new Intent(QuotesActivity.this, EditQuoteActivity.class);
                intent.putExtra(Constants.KEY_QUOTE, quote);
                startActivity(intent);
                //showToast("on Item Clicked");
            }

            @Override
            public void onItemDeleted(Quote quote) {
                showToast("on item Deleted");
                deleteQuote(quote);
            }

            @Override
            public void onItemEdit(Quote quote) {
                showToast("on item Edit");
            }
        });
        binding.quoteRv.setAdapter(quotesAdapter);
    }

    private void deleteQuote(Quote quote) {
        Call<Void> call = crudService.deleteQuote(quote.id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully completed");
                fetchQuotes();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed");
            }
        });
    }

    private void setupQuotesRv() {
        binding.quoteRv.setLayoutManager(new LinearLayoutManager(this));
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