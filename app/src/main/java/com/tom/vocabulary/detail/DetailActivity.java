package com.tom.vocabulary.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.tom.vocabulary.R;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();
    private TextView nameText;
    private TextView meansText;
    private MenuItem mStar;
    private DetailViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String name = getIntent().getStringExtra("WORD");
        Log.d(TAG, "onCreate: " + name);
        nameText = findViewById(R.id.name);
        meansText = findViewById(R.id.means);

        DetailViewModelFactory factory =
                DetailViewModelFactory.createFactory(getApplication(), name);
        viewModel = ViewModelProviders.of(this, factory)
        .get(DetailViewModel.class);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        mStar = menu.findItem(R.id.action_star);
        mStar.setOnMenuItemClickListener(menuItem -> {
            viewModel.setStar();
            return true;
        });
        viewModel.getWord().observe(this, word -> {
            Log.d(TAG, "onCreate: " + word.getMeans() );
            if (!word.getStar()) {
                mStar.setIcon(R.drawable.unstar);
            } else {
                mStar.setIcon(R.drawable.star);
            }
            nameText.setText(word.getName());
            meansText.setText(word.getMeans());
        });
        return super.onCreateOptionsMenu(menu);
    }
}
