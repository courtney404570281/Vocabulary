package com.tom.vocabulary;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
//public class WordViewModel extends ViewModel {
    private LiveData<List<Word>> wordsLiveData;
    private WordDao dao;
    public WordViewModel(@NonNull Application application) {
        super(application);
        dao = WordDatabase.getInstance(application.getApplicationContext())
                .wordDao();
        wordsLiveData = dao.getAll();
    }
//    private WordDao dao = WordDatabase.getInstance().wordDao()
    public LiveData<List<Word>> getWords() {
        return wordsLiveData;
    }

}
