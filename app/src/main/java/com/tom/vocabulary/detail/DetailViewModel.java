package com.tom.vocabulary.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tom.vocabulary.data.DataRepository;
import com.tom.vocabulary.data.Word;

public class DetailViewModel extends ViewModel {
    private LiveData<Word> wordLiveData;
    private final DataRepository repository;
    private final String name;

    public DetailViewModel(DataRepository repository, String name) {
        this.repository = repository;
        this.name = name;
    }

    public LiveData<Word> getWord() {
        wordLiveData = repository.getWord(name);
        return wordLiveData;
    }

    public void setStar() {
        repository.updateStar(name);
    }
}
