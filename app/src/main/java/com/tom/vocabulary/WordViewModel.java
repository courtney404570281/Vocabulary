package com.tom.vocabulary;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tom.vocabulary.data.DataRepository;
import com.tom.vocabulary.data.Word;
import com.tom.vocabulary.data.WordDao;

import java.util.List;

//public class WordViewModel extends AndroidViewModel {
public class WordViewModel extends ViewModel {
    private LiveData<List<Word>> wordsLiveData;
    private WordDao dao;
    private final DataRepository repository;

    public WordViewModel(DataRepository repository) {
        this.repository = repository;
        wordsLiveData = repository.getAll();
    }

    public LiveData<List<Word>> getWords() {
        return wordsLiveData;
    }

}
