package com.tom.vocabulary.game;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tom.vocabulary.R;
import com.tom.vocabulary.data.Word;

import java.util.List;

public class GameActivity extends AppCompatActivity {

    private TextView questionView;
    private TextView result;
    private GameViewModel viewModel;
    private WordAnswers answersView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameFactory factory = GameFactory.createFactory(getApplication());
        viewModel = ViewModelProviders.of(this, factory)
                .get(GameViewModel.class);

        setContentView(R.layout.activity_game);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        questionView = findViewById(R.id.question);
        result = findViewById(R.id.result);

        viewModel.getCurrentWord().observe(this, this::updateContent);
        viewModel.getResult().observe(this, this::showResults);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this::newGame);
    }

    private void newGame(View view) {
        result.setText(null);
        viewModel.resetGame();
    }

    private void loadRound(List<Word> words) {
        answersView.loadAnswers(words);
        viewModel.newGameRound();
    }

    private void showResults(Result res) {
        if (res == null)
            return;
        result.setText(getString(res.getResult()));
        result.setTextColor(ContextCompat.getColor(this, res.getColor()));

        if (!res.isEnable()) {
            answersView.setEnabled(false);
        }
    }

    private void updateContent(Word word) {
        if (word == null) {
            return;
        }
        questionView.setText(word.getMeans());
    }
}
