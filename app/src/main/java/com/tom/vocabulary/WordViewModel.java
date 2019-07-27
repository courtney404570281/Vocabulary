package com.tom.vocabulary;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class WordViewModel extends ViewModel {
    private LiveData<List<Word>> wordsLiveData;
//    private WordDao dao = WordDatabase.getInstance().wordDao()

    public WordViewModel() {

    }
}
