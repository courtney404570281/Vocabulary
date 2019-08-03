package com.tom.vocabulary;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tom.vocabulary.data.DataRepository;
import com.tom.vocabulary.data.Word;

import java.util.List;

public class SortViewModel extends ViewModel {
    private final DataRepository repository;
    private final String sortBy;
    LiveData<List<Word>> listLiveData;

    public SortViewModel(DataRepository repository, String sortBy) {
        this.repository = repository;
        this.sortBy = sortBy;
        listLiveData = repository.getSortWords(sortBy);
    }

    public LiveData<List<Word>> getWords() {
        return listLiveData;
    }
}
