package com.example.hoh.Todo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert
    void insert(Todo todo);

    @Update
    void update(Todo todo);

    @Delete
    void delete(Todo todo);

    @Query("Delete FROM todo_table")
    void deleteAllTodos();

    @Query("SELECT * FROM todo_table ORDER BY priority ASC")
    LiveData<List<Todo>> getAllTodos();

    @Query("SELECT * FROM todo_table WHERE title Like :title")
    List<Todo> getSearchTodos(String title);

    @Query("SELECT * FROM todo_table ORDER BY priority ASC")
    List<Todo> getBlankTodos();
}
