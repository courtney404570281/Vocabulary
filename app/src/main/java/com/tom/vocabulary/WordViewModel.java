package com.tom.vocabulary;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tom.vocabulary.data.Word;

import java.util.List;

//public class WordViewModel extends AndroidViewModel {
public class WordViewModel extends ViewModel {
    private LiveData<List<Word>> wordsLiveData;


    public LiveData<List<Word>> getWords() {
        return wordsLiveData;
    }

}
