package com.example.hoh.Main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hoh.Timetable.Timetable_Fragment;
import com.example.hoh.Todo.Todo_Fragment2;
import com.example.hoh.Map.Map_Fragment4;
import com.example.hoh.Notice.Notice_Fragment3;
import com.example.hoh.R;
import com.example.hoh.Setting.Setting;
import com.example.hoh.Weather.Weather_main;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Home extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Timetable_Fragment timetableFragment;
    Todo_Fragment2 todoFragment2;
    Notice_Fragment3 noticeFragment3;
    Map_Fragment4 mapFragment4;
    NetworkImageView weatherView;
    TextView temperatureView;
    RequestQueue queue;
    TextView today;
    ImageView setting;
    String city_name;
    String city_Dangjin = "Dangjin";
    String city_Cheonan = "Cheonan";
    String city_check;
    Intent intent;
    private SharedPreferences settings;
    private SharedPreferences.Editor editors;
    public static String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        userID = getIntent().getStringExtra("userID");
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        settings = getSharedPreferences("setting", 0);
        editors = settings.edit();
        intent = getIntent();
        if (city_check != intent.getStringExtra("city_name")) {
            editors.putString("city_name", intent.getStringExtra("city_name"));
            editors.commit();
        }
        city_name = settings.getString("city_name", "0");
        timetableFragment = new Timetable_Fragment();
        todoFragment2 = new Todo_Fragment2();
        noticeFragment3 = new Notice_Fragment3();
        mapFragment4 = new Map_Fragment4();
        weatherView = findViewById(R.id.weather_symbol1);
        temperatureView = findViewById(R.id.today_temperature);
        today = findViewById(R.id.today);
        setting = findViewById(R.id.setting);
        weatherView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent weatherIntent = new Intent(view.getContext(), Weather_main.class);
                Home.this.startActivity(weatherIntent);
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingIntent = new Intent(view.getContext(), Setting.class);
                Home.this.startActivity(settingIntent);
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.home_layout, timetableFragment).commitAllowingStateLoss();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tab1: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_layout, timetableFragment).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.tab2: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_layout, todoFragment2).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.tab3: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_layout, noticeFragment3).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.tab4: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_layout, mapFragment4).commitAllowingStateLoss();
                        return true;
                    }

                    default:
                        return false;
                }
            }
        });

    }

    protected void onResume() {
        super.onResume();
        queue = Volley.newRequestQueue(this);
        if (city_Dangjin.equals(city_name)) {
            StringRequest currentRequest = new StringRequest(Request.Method.POST, "https://api.openweathermap.org/data/2.5/weather?q=tangjin&mode=xml&units=metric&appid=2a0693060e6ad8a109af75afcb428f21", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    parseXMLCurrent(response);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(currentRequest);
        } else if (city_Cheonan.equals(city_name)) {
            StringRequest currentRequest = new StringRequest(Request.Method.POST, "https://api.openweathermap.org/data/2.5/weather?q=cheonan&mode=xml&units=metric&appid=2a0693060e6ad8a109af75afcb428f21", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    parseXMLCurrent(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(currentRequest);
        } else {
            StringRequest currentRequest = new StringRequest(Request.Method.POST, "https://api.openweathermap.org/data/2.5/weather?q=asan&mode=xml&units=metric&appid=2a0693060e6ad8a109af75afcb428f21", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    parseXMLCurrent(response);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(currentRequest);
        }
    }


    protected void onPause() {
        super.onPause();

    }

    private void parseXMLCurrent(String response) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(response)));
            doc.getDocumentElement().normalize();
            Element tempElement = (Element) (doc.getElementsByTagName("temperature").item(0));
            String temperature = tempElement.getAttribute("value");
            int temp = (int) Double.parseDouble(temperature);
            temperatureView.setText("" + temp);
            Element dayElement = (Element) (doc.getElementsByTagName("sun").item(0));
            String day = dayElement.getAttribute("set");
            day = day.substring(5, 10);
            today.setText(day);
            Element weatherElement = (Element) (doc.getElementsByTagName("weather").item(0));
            String symbol = weatherElement.getAttribute("icon");

            ImageLoader imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
                @Override
                public Bitmap getBitmap(String url) {
                    return null;
                }

                @Override
                public void putBitmap(String url, Bitmap bitmap) {

                }
            });
            weatherView.setImageUrl("https://openweathermap.org/img/wn/" + symbol + "@2x.png", imageLoader);
        } catch (Exception e) {
            e.printStackTrace();
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
