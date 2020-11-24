package com.example.hoh.Todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hoh.R;

public class TodoAdapter extends ListAdapter<Todo, TodoAdapter.TodoHolder> {
    private OnItemClickListener listener;

    public TodoAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Todo> DIFF_CALLBACK = new DiffUtil.ItemCallback<Todo>() {
        @Override
        public boolean areItemsTheSame(@NonNull Todo oldItem, @NonNull Todo newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Todo oldItem, @NonNull Todo newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getContext().equals(newItem.getContext()) &&
                    oldItem.getPriority() == newItem.getPriority() &&
                    oldItem.getDate().equals(newItem.getDate());

        }
    };

    @NonNull
    @Override
    public TodoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_item, parent, false);
        return new TodoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoHolder holder, int position) {
        Todo currentTodo = getItem(position);
        holder.textViewTitle.setText(currentTodo.getTitle());
        holder.textViewContent.setText(currentTodo.getContext());
        holder.textViewPriority.setText(String.valueOf(currentTodo.getPriority()));
        holder.textViewDate.setText(currentTodo.getDday());
    }


    public Todo getTodoAt(int position) {
        return getItem(position);
    }

    class TodoHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewContent;
        private TextView textViewPriority;
        private TextView textViewDate;


        public TodoHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.todo_item_title);
            textViewContent = itemView.findViewById(R.id.todo_item_content);
            textViewPriority = itemView.findViewById(R.id.todo_item_priority);
            textViewDate = itemView.findViewById(R.id.todo_item_dday);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Todo todo);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
