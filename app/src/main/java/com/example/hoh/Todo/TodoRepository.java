package com.example.hoh.Todo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TodoRepository {
    private TodoDao todoDao;
    private LiveData<List<Todo>> allTodos;

    public TodoRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        todoDao = database.todoDao();
        allTodos = todoDao.getAllTodos();
    }

    public void insert(Todo todo) {
        new InsertTodoAsyncTask(todoDao).execute(todo);
    }

    public void update(Todo todo) {
        new UpdateTodoAsyncTask(todoDao).execute(todo);
    }

    public void delete(Todo todo) {
        new DeleteTodoAsyncTask(todoDao).execute(todo);
    }

    public void deleteAllTodos() {
        new DeleteAllTodosAsyncTask(todoDao).execute();
    }

    public LiveData<List<Todo>> getAllTodos() {
        return allTodos;
    }

    public List<Todo> getSearchTodos(String title) {
        return todoDao.getSearchTodos(title);
    }

    public List<Todo> getBlankTodos() {
        return todoDao.getBlankTodos();
    }


    private static class InsertTodoAsyncTask extends AsyncTask<Todo, Void, Void> {
        private TodoDao todoDao;

        private InsertTodoAsyncTask(TodoDao todoDao) {
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            todoDao.insert(todos[0]);
            return null;
        }
    }

    private static class UpdateTodoAsyncTask extends AsyncTask<Todo, Void, Void> {
        private TodoDao todoDao;

        private UpdateTodoAsyncTask(TodoDao todoDao) {
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            todoDao.update(todos[0]);
            return null;
        }
    }

    private static class DeleteTodoAsyncTask extends AsyncTask<Todo, Void, Void> {
        private TodoDao todoDao;

        private DeleteTodoAsyncTask(TodoDao todoDao) {
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            todoDao.delete(todos[0]);
            return null;
        }
    }

    private static class DeleteAllTodosAsyncTask extends AsyncTask<Void, Void, Void> {
        private TodoDao todoDao;

        private DeleteAllTodosAsyncTask(TodoDao todoDao) {
            this.todoDao = todoDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            todoDao.deleteAllTodos();
            return null;
        }
    }

}
