package com.tom.vocabulary;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.tom.vocabulary.data.DataRepository;

import java.lang.reflect.InvocationTargetException;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final DataRepository repository;

    public ViewModelFactory(DataRepository repository) {
        this.repository = repository;
    }

    public static ViewModelFactory createFactory(Activity activity) {
        Application application = activity.getApplication();
        if (application == null) {
            throw new IllegalStateException("Your application is not ready");
        }
        return new ViewModelFactory(DataRepository.getInstance(application));
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(DataRepository.class)
                    .newInstance(repository);
        } catch (IllegalAccessException | InstantiationException |
                InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create " + modelClass);
        }
    }
}
