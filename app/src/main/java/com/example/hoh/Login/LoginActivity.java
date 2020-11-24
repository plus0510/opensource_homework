package com.example.hoh.Login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.hoh.Main.Home;
import com.example.hoh.R;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    public static Context context_main;
    private AlertDialog dialog;
    int logout_check = 0;
    Intent intent;
    public String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        context_main = this;
        final EditText idText = findViewById(R.id.idText);
        final EditText passwordText = findViewById(R.id.passwordText);
        final Button loginButton = findViewById(R.id.loginButton);
        final TextView passwordButton = findViewById(R.id.passwordButton);
        final TextView registerButton = findViewById(R.id.registerButton);
        final CheckBox autoLogin = findViewById(R.id.autologin);
        final SharedPreferences setting;
        final SharedPreferences.Editor editor;

        setting = getSharedPreferences("setting", 0);
        editor = setting.edit();
        intent = getIntent();
        if (logout_check != intent.getIntExtra("logout_check", 0)) {
            editor.clear();
            editor.commit();
        }
        autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                userID = idText.getText().toString();
                String PW = passwordText.getText().toString();
                if (isChecked) {
                    if (setting.getInt("auto_check", 1) == 0) {
                        Intent intent = new Intent(LoginActivity.this, Home.class);
                        intent.putExtra("userID", setting.getString("userID", ""));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        LoginActivity.this.startActivity(intent);
                        finish();
                    } else {
                        editor.putString("userID", userID);
                        editor.putString("userPW", PW);
                        editor.putInt("auto_check", 0);
                        editor.putBoolean("autoLogin_enabled", true);
                        editor.commit();

                    }
                } else {
                    editor.remove("auto_check");
                    editor.remove("userID");
                    editor.remove("userPW");
                    editor.remove("autoLogin_enabled");
                    editor.clear();
                    editor.commit();
                }
            }
        });

        if (setting.getBoolean("autoLogin_enabled", false)) {
            idText.setText(setting.getString("userID", ""));
            passwordText.setText(setting.getString("userPW", ""));
            autoLogin.setChecked(true);
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userID = idText.getText().toString();
                final String userPassword = passwordText.getText().toString();

                Response.Listener<String> responseLister = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            if (success) {
                                dialog = builder.setMessage("로그인에 성공했습니다.").setPositiveButton("확인", null).create();
                                dialog.show();
                                Intent intent = new Intent(LoginActivity.this, Home.class);
                                editor.putString("userID", userID);
                                editor.putString("userPW", userPassword);
                                editor.commit();
                                intent.putExtra("userID", userID);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                LoginActivity.this.startActivity(intent);
                                finish();
                            } else {
                                dialog = builder.setMessage("계정을 다시 확인하세요").setNegativeButton("다시 시도", null).create();
                                dialog.show();
                                autoLogin.setChecked(false);

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseLister);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        });

        passwordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SearchPassword.class);
                LoginActivity.this.startActivity(intent);
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

    private long lastTimeBackPressed;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastTimeBackPressed < 1500) {
            android.os.Process.killProcess(android.os.Process.myPid());
            return;
        }
        Toast.makeText(this, "뒤로 버튼을 한 번 더 눌러 종료합니다", Toast.LENGTH_SHORT).show();
        lastTimeBackPressed = System.currentTimeMillis();

    }
}

