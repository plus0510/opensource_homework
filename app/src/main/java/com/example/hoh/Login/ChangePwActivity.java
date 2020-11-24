package com.example.hoh.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.hoh.R;

import org.json.JSONObject;

public class ChangePwActivity extends AppCompatActivity {
    private String userID;
    private String userPwd;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_changepw);

        final TextView idText = findViewById(R.id.idText);
        final EditText changePw = findViewById(R.id.changePw);
        final Button changePwButton = findViewById(R.id.changeButton);
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        idText.setText(userID);

        changePwButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                userID = idText.getText().toString();
                userPwd = changePw.getText().toString();

                Response.Listener<String> responseLister = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                Intent intent2 = new Intent(ChangePwActivity.this, LoginActivity.class);
                                ChangePwActivity.this.startActivity(intent2);
                                finish();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ChangePwActivity.this);
                                dialog = builder.setMessage("다시 입력하세요").setNegativeButton("다시 시도", null).create();
                                dialog.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                ChangePwRequest changePwRequest = new ChangePwRequest(userID, userPwd, responseLister);
                RequestQueue queue = Volley.newRequestQueue(ChangePwActivity.this);
                queue.add(changePwRequest);
            }
        });
    }
}