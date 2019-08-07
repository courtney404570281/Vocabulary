package com.tom.vocabulary;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.tom.vocabulary.data.DataRepository;
import com.tom.vocabulary.data.Word;

import java.util.List;

public class SortViewModel extends ViewModel {
    private final DataRepository repository;
    private final String sortBy;
    LiveData<List<Word>> listLiveData;
    MutableLiveData<String> liveSortBy = new MutableLiveData<>();

    public SortViewModel(DataRepository repository, String sortBy) {
        this.repository = repository;
        this.sortBy = sortBy;
        liveSortBy.setValue(sortBy);
        listLiveData = Transformations.switchMap(liveSortBy, sort ->
            repository.getSortWords(sort));
    }

    public LiveData<List<Word>> getWords() {
        return listLiveData;
    }

    public void updateSortBy(String sortBy) {
        liveSortBy.setValue(sortBy);
    }
}
