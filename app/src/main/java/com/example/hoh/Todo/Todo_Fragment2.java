package com.example.hoh.Todo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hoh.R;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class Todo_Fragment2 extends Fragment {
    ViewGroup viewGroup;

    private TodoViewModel todoViewModel;

    public static final int ADD_TODO_REQUEST = 1;
    public static final int EDIT_TODO_REQUEST = 2;

    private EditText todoSearchEdt;
    private Button todoSearchBtn;
    private ImageView todoAdd;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.todo_fragment2, container, false);

        todoSearchEdt = viewGroup.findViewById(R.id.todo_search);
        todoSearchBtn = viewGroup.findViewById(R.id.todo_search_btn);
        todoAdd = viewGroup.findViewById(R.id.todo_add_btn);


        todoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddEditTodoActivity.class);
                startActivityForResult(intent, ADD_TODO_REQUEST);
            }
        });


        RecyclerView recyclerView = viewGroup.findViewById(R.id.todo_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        final TodoAdapter adapter = new TodoAdapter();
        recyclerView.setAdapter(adapter);

        todoViewModel = new ViewModelProvider(getActivity(), ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(TodoViewModel.class);
        todoViewModel.getAllTodos().observe(getActivity(), new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                adapter.submitList(todos);
            }
        });

        todoSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (todoSearchEdt.getText().toString().equals("")) {
                    List<Todo> todos = todoViewModel.getBlankTodos();
                    adapter.submitList(todos);
                } else {
                    List<Todo> todos = todoViewModel.getSearchTodos(todoSearchEdt.getText().toString());
                    adapter.submitList(todos);
                }

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                todoViewModel.delete(adapter.getTodoAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "할 일이 삭제되었습니다.", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new TodoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Todo todo) {
                Intent intent = new Intent(getActivity(), AddEditTodoActivity.class);
                intent.putExtra(AddEditTodoActivity.EXTRA_ID, todo.getId());
                intent.putExtra(AddEditTodoActivity.EXTRA_TITLE, todo.getTitle());
                intent.putExtra(AddEditTodoActivity.EXTRA_CONTEXT, todo.getContext());
                intent.putExtra(AddEditTodoActivity.EXTRA_PRIORITY, todo.getPriority());
                intent.putExtra(AddEditTodoActivity.EXTRA_DATE, todo.getDate());
                startActivityForResult(intent, EDIT_TODO_REQUEST);

            }
        });


        return viewGroup;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_TODO_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddEditTodoActivity.EXTRA_TITLE);
            String context = data.getStringExtra(AddEditTodoActivity.EXTRA_CONTEXT);
            int priority = data.getIntExtra(AddEditTodoActivity.EXTRA_PRIORITY, 1);
            String date = data.getStringExtra(AddEditTodoActivity.EXTRA_DATE);
            String dday = data.getStringExtra(AddEditTodoActivity.EXTRA_DDAY);

            Todo todo = new Todo(title, context, priority, date, dday);
            todoViewModel.insert(todo);

            Toast.makeText(getActivity(), "저장 성공", Toast.LENGTH_SHORT).show();

        } else if (requestCode == EDIT_TODO_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditTodoActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(getActivity(), "할 일 수정 안됨", Toast.LENGTH_SHORT).show();
                return;
            }
            String title = data.getStringExtra(AddEditTodoActivity.EXTRA_TITLE);
            String context = data.getStringExtra(AddEditTodoActivity.EXTRA_CONTEXT);
            int priority = data.getIntExtra(AddEditTodoActivity.EXTRA_PRIORITY, 1);
            String date = data.getStringExtra(AddEditTodoActivity.EXTRA_DATE);
            String dday = data.getStringExtra(AddEditTodoActivity.EXTRA_DDAY);

            Todo todo = new Todo(title, context, priority, date, dday);
            todo.setId(id);
            todoViewModel.update(todo);
            Toast.makeText(getActivity(), "할 일 수정 됨", Toast.LENGTH_SHORT).show();
        } else {

        }
    }


}








