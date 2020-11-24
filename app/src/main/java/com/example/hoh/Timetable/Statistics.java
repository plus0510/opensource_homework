package com.example.hoh.Timetable;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hoh.Main.Home;
import com.example.hoh.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Statistics extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable_statistics);
    }

    private ListView courseListView;
    private StatisticsCourseListAdapter adapter;
    private List<Course> courseList;

    public static int totalCredit = 0;
    public static TextView credit;


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        courseListView = findViewById(R.id.courseListView);
        courseList = new ArrayList<Course>();
        adapter = new StatisticsCourseListAdapter(getApplicationContext(), courseList, this);
        courseListView.setAdapter(adapter);
        new BackgroundTask().execute();
        totalCredit = 0;
        credit = findViewById(R.id.totalCredit);
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target2;

        @Override
        protected void onPreExecute() {
            try {
                target2 = "https://cksrudzz.cafe24.com/StatisticsCourseList.php?userID=" + URLEncoder.encode(Home.userID, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target2);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
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
                int courseID;
                String courseTitle;
                String courseDivide;
                String courseTime;
                String courseProfessor;
                String courseRoom;
                String courseRoom2;
                String courseRoom3;
                String courseRoom4;
                int courseCredit;
                while (count < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(count);
                    courseID = object.getInt("courseID");
                    courseTime = object.getString("courseTime");
                    courseProfessor = object.getString("courseProfessor");
                    courseTitle = object.getString("courseTitle");
                    courseRoom = object.getString("courseRoom");
                    courseRoom2 = object.getString("courseRoom2");
                    courseRoom3 = object.getString("courseRoom3");
                    courseRoom4 = object.getString("courseRoom4");
                    courseDivide = object.getString("courseDivide");
                    courseCredit = object.getInt("courseCredit");
                    totalCredit += courseCredit;
                    courseList.add(new Course(courseID, courseTitle, courseDivide, courseProfessor, courseTime, courseCredit, courseRoom, courseRoom2, courseRoom3, courseRoom4));
                    count++;
                }
                adapter.notifyDataSetChanged();
                credit.setText(totalCredit + "학점");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
