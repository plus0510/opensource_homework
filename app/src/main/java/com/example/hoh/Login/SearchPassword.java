package com.example.hoh.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.hoh.R;

import org.json.JSONObject;

public class SearchPassword extends AppCompatActivity {
    private String userName;
    private String passwordHint;
    private String passwordHintAnswer;
    private ArrayAdapter passwordQuestionList;
    private Spinner passwordQuestion;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_searchpassword);


        passwordQuestion = findViewById(R.id.passwordquestionSpinner);
        passwordQuestionList = ArrayAdapter.createFromResource(this, R.array.password, android.R.layout.simple_spinner_dropdown_item);
        passwordQuestion.setAdapter(passwordQuestionList);

        final EditText idText = findViewById(R.id.idText);
        final EditText nameText = findViewById(R.id.nameText);
        final Button registerButton = findViewById(R.id.registerButton);
        final EditText hintAnswer = findViewById(R.id.passwordquestionText);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userID = idText.getText().toString();
                userName = nameText.getText().toString();
                passwordHint = passwordQuestion.getSelectedItem().toString();
                passwordHintAnswer = hintAnswer.getText().toString();


                Response.Listener<String> responseLister = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                Intent intent = new Intent(SearchPassword.this, ChangePwActivity.class);
                                intent.putExtra("userID", idText.getText().toString());
                                SearchPassword.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SearchPassword.this);
                                dialog = builder.setMessage("다시 입력하세요").setNegativeButton("다시 시도", null).create();
                                dialog.show();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                FindPassword findPassword = new FindPassword(userID, userName, passwordHint, passwordHintAnswer, responseLister);
                RequestQueue queue = Volley.newRequestQueue(SearchPassword.this);
                queue.add(findPassword);
            }
        });
    }
}
