package com.example.hoh.Todo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "todo_table")
public class Todo {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String context;
    private int priority;
    private String date;
    private String Dday;

    public Todo(String title, String context, int priority, String date, String Dday) {
        this.title = title;
        this.context = context;
        this.priority = priority;
        this.date = date;
        this.Dday = Dday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getPriority() {
        return priority;
    }

    public String getDate() {
        return date;
    }

    public String getDday() {
        return Dday;
    }


}
