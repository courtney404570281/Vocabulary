package com.tom.vocabulary;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Word.class}, version = 1)
public abstract class WordDatabase extends RoomDatabase {
    public abstract WordDao wordDao();
    private static volatile WordDatabase instance = null;

    public static WordDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context,
                    WordDatabase.class,
                    "word-database")
                    .build();
        }
        return instance;
    }
}
