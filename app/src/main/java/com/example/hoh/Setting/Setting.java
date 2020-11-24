package com.example.hoh.Setting;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.hoh.Login.LoginActivity;
import com.example.hoh.R;
import com.example.hoh.Main.Home;
import com.example.hoh.Todo.TodoViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

public class Setting extends AppCompatActivity {
    public RadioGroup city;
    String city_name;
    Button pass;
    TextView name, num, major, attend, lib, hub, logout, delId, tel;
    private TodoViewModel todoViewModel;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        city = findViewById(R.id.weather_radio);
        pass = findViewById(R.id.pass);
        delId = findViewById(R.id.delid);
        logout = findViewById(R.id.logout);
        name = findViewById(R.id.setname);
        num = findViewById(R.id.setnum);
        tel = findViewById(R.id.tel);
        major = findViewById(R.id.setmajor);
        attend = findViewById(R.id.attendbutton);
        lib = findViewById(R.id.libbutton);
        hub = findViewById(R.id.hubbutton);

        todoViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(TodoViewModel.class);

        num.setText(((LoginActivity) LoginActivity.context_main).userID);

        attend.setText("전자출결 다운로드");
        lib.setText("중앙도서관 다운로드");
        hub.setText("호서버스 다운로드");
        Linkify.TransformFilter mTransform = new Linkify.TransformFilter() {
            @Override
            public String transformUrl(Matcher match, String url) {
                return "";
            }
        };
        Pattern pattern1 = Pattern.compile("전자출결 다운로드");
        Pattern pattern2 = Pattern.compile("중앙도서관 다운로드");
        Pattern pattern3 = Pattern.compile("호서버스 다운로드");

        Linkify.addLinks(attend, pattern1, "https://play.google.com/store/apps/details?id=com.hoseo.hoseo_abookn", null, mTransform);
        Linkify.addLinks(lib, pattern2, "https://play.google.com/store/apps/details?id=kr.ac.hoseo.dlibrary", null, mTransform);
        Linkify.addLinks(hub, pattern3, "https://play.google.com/store/apps/details?id=kr.ac.hoseo.hoseobus", null, mTransform);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this, LoginActivity.class);
                intent.putExtra("logout_check", 1);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                Setting.this.startActivity(intent);
                finish();
            }
        });

        delId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this, LoginActivity.class);
                intent.putExtra("logout_check", 1);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                Setting.this.startActivity(intent);
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.context_main);
                                AlertDialog dialog = builder.setMessage("회원탈퇴가 성공되었습니다.").setPositiveButton("확인", null).create();
                                dialog.show();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.context_main);
                                AlertDialog dialog = builder.setMessage("회원탈퇴가 실패하였습니다.").setNegativeButton("다시시도", null).create();
                                dialog.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                DeleteUserRequest deleteUserRequest = new DeleteUserRequest(Home.userID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Setting.this);
                queue.add(deleteUserRequest);
                todoViewModel.deleteAllTodos();
                finish();

            }
        });
        city.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.asan:
                        city_name = "Asan";

                        break;
                    case R.id.cheonan:
                        city_name = "Cheonan";

                        break;
                    case R.id.dangjin:
                        city_name = "Dangjin";

                        break;
                }
            }
        });
        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this, Home.class);
                intent.putExtra("city_name", city_name);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                Setting.this.startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        new BackgroundTask().execute();
        new BackgroundTask2().execute();
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            try {
                target = "https://cksrudzz.cafe24.com/UserIndex.php?userID=" + URLEncoder.encode(((LoginActivity) LoginActivity.context_main).userID, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                InputStream inputStream = httpsURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpsURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
        }

        @Override
        public void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;

                String userName;
                String userMajor;

                while (count < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(count);
                    userName = object.getString("userName");
                    userMajor = object.getString("userMajor");
                    name.setText(userName);
                    major.setText(userMajor);
                    count++;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class BackgroundTask2 extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            try {
                target = "https://cksrudzz.cafe24.com/tellindex.php?userID=" + URLEncoder.encode(((LoginActivity) LoginActivity.context_main).userID, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                InputStream inputStream = httpsURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpsURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
        }

        @Override
        public void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;

                String MAJOR_NUM;

                while (count < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(count);
                    MAJOR_NUM = object.getString("MAJOR_NUM");
                    tel.setText(MAJOR_NUM);
                    count++;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
