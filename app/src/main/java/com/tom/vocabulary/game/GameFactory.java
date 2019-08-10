package com.tom.vocabulary.game;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.tom.vocabulary.data.DataRepository;

import java.lang.reflect.InvocationTargetException;

public class GameFactory implements ViewModelProvider.Factory {
    private final DataRepository repository;
    private final int limit;

    public GameFactory(DataRepository repository, int limit) {
        this.repository = repository;
        this.limit = limit;
    }

    public static GameFactory createFactory(Application application) {
        if (application == null) {
            throw new IllegalStateException("Application not ready");
        }

        return new GameFactory(
                DataRepository.getInstance(application), 3);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(DataRepository.class, int.class)
                    .newInstance(repository, limit);
        } catch (NoSuchMethodException | IllegalAccessException |
                InstantiationException | InvocationTargetException e) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
        }
    }
}
