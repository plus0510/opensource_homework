package com.example.hoh.Login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.hoh.R;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private ArrayAdapter passwordQuestionList;
    private ArrayAdapter majorList;
    private Spinner passwordQuestion;
    private Spinner major;
    private String userID;
    private String userName;
    private String userPwd;
    private String userMajor;
    private String passwordHintAnswer;
    private String passwordHint;
    private AlertDialog dialog;
    private boolean validate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_register);
        major = findViewById(R.id.majorSpinner);
        passwordQuestion = findViewById(R.id.passwordquestionSpinner);
        passwordQuestionList = ArrayAdapter.createFromResource(this, R.array.password, android.R.layout.simple_spinner_dropdown_item);
        majorList = ArrayAdapter.createFromResource(this, R.array.major, android.R.layout.simple_spinner_dropdown_item);
        major.setAdapter(majorList);
        passwordQuestion.setAdapter(passwordQuestionList);
        final EditText idText = findViewById(R.id.idText);
        final EditText nameText = findViewById(R.id.nameText);
        final EditText passwordText = findViewById(R.id.passwordText);
        final EditText hintAnswer = findViewById(R.id.passwordquestionText);
        final Button registerButton = findViewById(R.id.registerButton);
        final Button validateButton = findViewById(R.id.validateButton);
        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userID = idText.getText().toString();
                if (validate) return;
                if (userID.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("아이디는 빈 칸일 수 없습니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                    return;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("사용할 수 있는 학번입니다.").setPositiveButton("확인", null).create();
                                dialog.show();
                                idText.setEnabled(false);
                                validate = true;
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("사용할 수 없는 학번입니다.").setNegativeButton("확인", null).create();
                                dialog.show();

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                ValidateRequest validateRequest = new ValidateRequest(userID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(validateRequest);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userID = idText.getText().toString();
                userName = nameText.getText().toString();
                userPwd = passwordText.getText().toString();
                userMajor = major.getSelectedItem().toString();
                passwordHint = passwordQuestion.getSelectedItem().toString();
                passwordHintAnswer = hintAnswer.getText().toString();

                if (!validate) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("먼저 중복 체크를 해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }
                if (userID.equals("") || userName.equals("") || userPwd.equals("") || userMajor.equals("") || passwordHint.equals("") || passwordHintAnswer.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("빈 칸 없이 입력해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("회원 등록에 성공했습니다.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                                finish();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("회원 등록에 실패했습니다.")
                                        .setNegativeButton("확인", null)
                                        .create();
                                dialog.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(userID, userName, userPwd, userMajor, passwordHint, passwordHintAnswer, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
