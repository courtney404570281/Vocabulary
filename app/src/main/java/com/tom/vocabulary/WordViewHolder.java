package com.tom.vocabulary;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tom.vocabulary.data.Word;

public class WordViewHolder extends RecyclerView.ViewHolder {
    TextView nameTest;
    ImageView starImage;
    WordAdapter.OnWordClickListener onWordClickAction;

    public void setOnWordClickAction(WordAdapter.OnWordClickListener onWordClickAction) {
        this.onWordClickAction = onWordClickAction;
    }

    public WordViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTest = itemView.findViewById(R.id.item_name);
        starImage = itemView.findViewById(R.id.item_star);
    }

    public void setWord(Word word) {
        nameTest.setText(word.getName());
        itemView.setOnClickListener(view -> {
            if (onWordClickAction != null){
                onWordClickAction.wordClicked(word.getName());
            }
        });
        if (word.getStar()) {
            starImage.setImageResource(R.drawable.star_red);
        }else {
            starImage.setImageResource(R.drawable.unstar);
        }
    }
}
