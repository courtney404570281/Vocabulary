package com.tom.vocabulary.detail;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.tom.vocabulary.data.DataRepository;

import java.lang.reflect.InvocationTargetException;

public class DetailViewModelFactory implements ViewModelProvider.Factory {
    private final DataRepository mRepository;
    private final String mName;

    public DetailViewModelFactory(DataRepository mRepository, String mName) {
        this.mRepository = mRepository;
        this.mName = mName;
    }

    public static DetailViewModelFactory createFactory(Application application,
                                                       String name) {
        if (application == null) {
            throw new IllegalStateException("Application not ready");
        }
        return new DetailViewModelFactory(
                DataRepository.getInstance(application),name);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(DataRepository.class, String.class)
                    .newInstance(mRepository, mName);
        } catch (IllegalAccessException | InstantiationException |
                InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create " + modelClass);
        }
    }
}
