package com.tom.vocabulary.game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.TextView;

import com.tom.vocabulary.R;

public class GuessActivity extends AppCompatActivity {

    private GuessViewModel viewModel;
    private TextView secretText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);
        secretText = findViewById(R.id.secret);


        viewModel = ViewModelProviders.of(this)
                .get(GuessViewModel.class);
        viewModel.getSecret().observe(this, this::updateSecret);
        viewModel.startGame();
    }

    private void updateSecret(String s) {
        secretText.setText(s);
    }
}
