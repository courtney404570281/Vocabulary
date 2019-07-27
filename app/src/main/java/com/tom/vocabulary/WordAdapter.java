package com.tom.vocabulary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordViewHolder> {
    List<Word> words;

    public WordAdapter(List<Word> words) {
        this.words = words;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word word = words.get(position);
        if (word != null) {
            holder.setWord(word);
        }
    }

    @Override
    public int getItemCount() {
        return words == null ? 0 : words.size();
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }
}
