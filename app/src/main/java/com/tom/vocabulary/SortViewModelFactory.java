package com.tom.vocabulary;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.tom.vocabulary.data.DataRepository;
import java.lang.reflect.InvocationTargetException;

public class SortViewModelFactory implements ViewModelProvider.Factory {
    private final DataRepository repository;
    private final String sortBy;

    public SortViewModelFactory(DataRepository repository, String sortBy) {
        this.repository = repository;
        this.sortBy = sortBy;
    }
    public static SortViewModelFactory createFactory(Application application,
                                                       String sortBy) {
        if (application == null) {
            throw new IllegalStateException("Application not ready");
        }
        return new SortViewModelFactory(
                DataRepository.getInstance(application), sortBy);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(DataRepository.class, String.class)
                    .newInstance(repository, sortBy);
        } catch (IllegalAccessException | InstantiationException |
                InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create " + modelClass);
        }
    }
}
