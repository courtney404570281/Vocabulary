package com.tom.vocabulary.data;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteQueryBuilder;

import com.tom.vocabulary.WordApplication;

import java.util.List;
import java.util.concurrent.Callable;
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

    public LiveData<Word> getWord(String name) {
        return dao.get(name);
    }

    public void updateStar(String name) {
        ioExecutor.submit(new Runnable() {
            @Override
            public void run() {
                dao.updateStar(name);
            }
        });

    }

    public LiveData<List<Word>> getSortWords(String sortBy) {
        //TODO: sqlitequery
        SupportSQLiteQueryBuilder builder =
                SupportSQLiteQueryBuilder.builder("Word");
        switch (sortBy) {
            case "NAME":
                builder.orderBy("name");
                break;
            case "STAR":
                builder.orderBy("star DESC");
                break;
        }
        Callable<LiveData<List<Word>>> callable = new Callable<LiveData<List<Word>>>() {
            @Override
            public LiveData<List<Word>> call() throws Exception {
                return dao.getSortWords(builder.create());
            }
        };
        try {
            return ioExecutor.submit(callable).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LiveData<List<Word>> getRandomWords(int limit) {
        return dao.getRandom(limit);
    }
}
