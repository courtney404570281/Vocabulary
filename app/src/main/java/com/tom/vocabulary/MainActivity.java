package com.tom.vocabulary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.tom.vocabulary.data.Word;
import com.tom.vocabulary.detail.DetailActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private WordAdapter adapter;
    private SortViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WordAdapter(null);
        recyclerView.setAdapter(adapter);
        adapter.setOnWordClickListener(new WordAdapter.OnWordClickListener() {
            @Override
            public void wordClicked(String name) {
                Intent intent = new Intent(
                        MainActivity.this, DetailActivity.class);
                intent.putExtra("WORD", name);
                startActivity(intent);
            }
        });
        String sortBy = PreferenceManager.getDefaultSharedPreferences(this)
                .getString("sort", "DEFAULT");
        Log.d(TAG, "onCreate: " + sortBy);
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);

        SortViewModelFactory factory = SortViewModelFactory.createFactory(getApplication(), sortBy);
        viewModel = ViewModelProviders.of(this, factory)
                .get(SortViewModel.class);
        viewModel.getWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                adapter.setWords(words);
                adapter.notifyDataSetChanged();
            }
        });
        /*
        new Thread(() -> {
            List<Word> words = WordDatabase.getInstance(this)
                    .wordDao()
                    .getAll();
            adapter = new WordAdapter(words);
            recyclerView.setAdapter(adapter);
        }).start();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("sort")) {
            viewModel.updateSortBy(sharedPreferences.getString(key, "DEFAULT"));
        }
    }
}
