package com.tom.vocabulary.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.tom.vocabulary.R;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String name = getIntent().getStringExtra("WORD");
        Log.d(TAG, "onCreate: " + name);
        DetailViewModelFactory factory =
                DetailViewModelFactory.createFactory(getApplication(), name);
        DetailViewModel viewModel =
                ViewModelProviders.of(this, factory)
                .get(DetailViewModel.class);
        viewModel.getWord().observe(this, word -> {
            Log.d(TAG, "onCreate: " + word.getMeans() );
        });
    }
}
