package com.example.hoh.Todo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hoh.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddEditTodoActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "com.example.hoh.Todo.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.example.hoh.Todo.EXTRA_TITLE";
    public static final String EXTRA_CONTEXT = "com.example.hoh.Todo.EXTRA_CONTEXT";
    public static final String EXTRA_PRIORITY = "com.example.hoh.Todo.EXTRA_PRIORITY";
    public static final String EXTRA_DATE = "com.example.hoh.Todo.EXTRA_DATE";
    public static final String EXTRA_DDAY = "com.example.hoh.Todo.EXTRA_DDAY";

    private EditText editTitle;
    private EditText editContext;
    private NumberPicker numberPickerPriority;
    private DatePicker editDateP, currentDateP;
    private String date, dateUpdate;
    private ImageView savetodo, closetodo;
    private TextView todotext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_add_activity);

        editTitle = findViewById(R.id.edit_title);
        editContext = findViewById(R.id.edit_context);
        numberPickerPriority = findViewById(R.id.number_picker_priority);
        currentDateP = findViewById(R.id.current_date);
        editDateP = findViewById(R.id.edit_date);
        savetodo = findViewById(R.id.todo_save);
        closetodo = findViewById(R.id.close_todo);
        todotext = findViewById(R.id.todo_text);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

        editDateP.setMinDate(System.currentTimeMillis() - 1000);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            todotext.setText("할 일 수정");
            editTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editContext.setText(intent.getStringExtra(EXTRA_CONTEXT));
            numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
            dateUpdate = intent.getStringExtra(EXTRA_DATE);
            int duYear = Integer.parseInt(dateUpdate.substring(0, 4));
            int duMonth = Integer.parseInt(dateUpdate.substring(5, 6));
            int duDay = Integer.parseInt(dateUpdate.substring(7, dateUpdate.length()));
            editDateP.updateDate(duYear, duMonth - 1, duDay);


        } else {
            todotext.setText("할 일 추가");
        }

        savetodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
        closetodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void saveNote() {
        String title = editTitle.getText().toString();
        String context = editContext.getText().toString();
        int priority = numberPickerPriority.getValue();

        int year = editDateP.getYear();
        int month = editDateP.getMonth();
        int day = editDateP.getDayOfMonth();

        Calendar mCalendar = new GregorianCalendar(currentDateP.getYear(), currentDateP.getMonth(), currentDateP.getDayOfMonth());
        Calendar mEndCalendar = new GregorianCalendar(year, month, day);

        long diffDay = mEndCalendar.getTimeInMillis() - mCalendar.getTimeInMillis();
        date = String.format("%d-%d-%d", year, month + 1, day);
        String dDay = Long.toString(diffDay / (1000 * 60 * 60 * 24));

        if (title.trim().isEmpty() || context.trim().isEmpty()) {
            Toast.makeText(this, "할 일 제목과 내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_CONTEXT, context);
        data.putExtra(EXTRA_PRIORITY, priority);
        data.putExtra(EXTRA_DATE, date);
        data.putExtra(EXTRA_DDAY, dDay);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

}
