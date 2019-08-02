package com.tom.vocabulary.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDao {
    @Query("select * from word")
    LiveData<List<Word>> getAll();

    @Query("select * from word where name = :name")
    Word get(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Word word);

    @Delete
    void delete(Word word);
}
