package com.tom.vocabulary.game;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.tom.vocabulary.R;
import com.tom.vocabulary.data.Word;

import java.util.List;

public class WordAnswers extends RadioGroup implements RadioGroup.OnCheckedChangeListener {
    public WordAnswers(Context context) {
        super(context);
    }

    public WordAnswers(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void loadAnswers(List<Word> words) {
        if (words != null) {
            removeAllViews();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            for (Word word : words) {
                RadioButton radioButton =
                        (RadioButton) inflater.inflate(R.layout.radio_answer, this, false);
                radioButton.setText(word.getName());
                radioButton.setTag(R.string.answer_tag, word.getName());
                addView(radioButton);
            }
        } else
            return;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {
        RadioButton button = radioGroup.findViewById(id);
        onAnswerPickedListener.onAnswerPicked((String) button.getTag(R.string.answer_tag));
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setEnabled(enabled);
        }
    }

    public interface OnAnswerPickedListener {
        void onAnswerPicked(String name);
    }

    OnAnswerPickedListener onAnswerPickedListener;

    public void setOnAnswerPickedListener(OnAnswerPickedListener onAnswerPickedListener) {
        this.onAnswerPickedListener = onAnswerPickedListener;
    }
}
