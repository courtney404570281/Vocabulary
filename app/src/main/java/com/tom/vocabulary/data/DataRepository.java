package com.tom.vocabulary.data;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataRepository {
    WordDao dao;
    ExecutorService ioExecutor;
    private static DataRepository instance;

    public static DataRepository getInstance(Application application) {
        if (instance == null) {
            WordDatabase database = WordDatabase.getInstance(application);
            instance = new DataRepository(database.wordDao(),
                    Executors.newSingleThreadExecutor());
        }
        return instance;
    }

    public DataRepository(WordDao dao, ExecutorService ioExecutor) {
        this.dao = dao;
        this.ioExecutor = ioExecutor;
    }

    public LiveData<List<Word>> getAll() {
        try {
            return ioExecutor.submit(dao::getAll).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
