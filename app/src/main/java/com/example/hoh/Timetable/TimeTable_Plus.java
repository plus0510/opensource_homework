package com.example.hoh.Timetable;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hoh.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class TimeTable_Plus extends AppCompatActivity {
    private ArrayAdapter yearAdapter;
    private Spinner yearSpinner;
    private ArrayAdapter termAdapter;
    private Spinner termSpinner;
    private ArrayAdapter areaAdapter;
    private Spinner areaSpinner;
    private ArrayAdapter majorAdapter;
    private Spinner majorSpinner;
    private EditText searchTitle;

    private ListView courseListView;
    private CourseListAdapter adapter;
    private List<Course> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable_plus);
        yearSpinner = findViewById(R.id.yearSpinner);
        termSpinner = findViewById(R.id.termSpinner);
        areaSpinner = findViewById(R.id.areaSpinner);
        majorSpinner = findViewById(R.id.majorsSpinner);
        searchTitle = findViewById(R.id.searchTitle);
        Button courseSearch = findViewById(R.id.courseSearch);
        Button searchButton = findViewById(R.id.serchButton);
        yearAdapter = ArrayAdapter.createFromResource(this, R.array.year, R.layout.support_simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);
        termAdapter = ArrayAdapter.createFromResource(this, R.array.term, R.layout.support_simple_spinner_dropdown_item);
        termSpinner.setAdapter(termAdapter);
        areaAdapter = ArrayAdapter.createFromResource(this, R.array.universityArea, R.layout.support_simple_spinner_dropdown_item);
        areaSpinner.setAdapter(areaAdapter);
        majorAdapter = ArrayAdapter.createFromResource(this, R.array.universityMajor, android.R.layout.simple_spinner_dropdown_item);
        majorSpinner.setAdapter(majorAdapter);


        courseListView = findViewById(R.id.courseListView);
        courseList = new ArrayList<Course>();
        adapter = new CourseListAdapter(getApplicationContext(), courseList, this);
        courseListView.setAdapter(adapter);


        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new BackgroundTask().execute();
            }
        });


        courseSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BackgroundTask2().execute();
            }
        });
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String target;

        @Override
        protected void onPreExecute() {
            try {
                target = "https://cksrudzz.cafe24.com/area.php?courseYear=" + URLEncoder.encode(yearSpinner.getSelectedItem().toString(), "utf-8") + "&courseTerm=" + URLEncoder.encode(termSpinner.getSelectedItem().toString(), "utf-8") +
                        "&courseArea=" + URLEncoder.encode(areaSpinner.getSelectedItem().toString(), "utf-8") + "&courseMajor=" + URLEncoder.encode(majorSpinner.getSelectedItem().toString(), "utf-8");
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
                courseList.clear();
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                int courseID;
                String courseYear;
                String courseTerm;
                String courseArea;
                String courseMajor;
                String courseTitle;
                int courseCredit;
                String courseDivide;
                String courseProfessor;
                String courseTime;
                String courseRoom;

                while (count < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(count);
                    courseID = object.getInt("courseID");
                    courseYear = object.getString("courseYear");
                    courseTerm = object.getString("courseTerm");
                    courseArea = object.getString("courseArea");
                    courseMajor = object.getString("courseMajor");
                    courseTitle = object.getString("courseTitle");
                    courseCredit = object.getInt("courseCredit");
                    courseDivide = object.getString("courseDivide");
                    courseProfessor = object.getString("courseProfessor");
                    courseTime = object.getString("courseTime");
                    courseRoom = object.getString("courseRoom");
                    Course course = new Course(courseID, courseYear, courseTerm, courseArea, courseMajor, courseTitle, courseCredit, courseDivide, courseProfessor, courseTime, courseRoom);
                    courseList.add(course);
                    count++;
                }
                if (count == 0) {
                    AlertDialog dialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(TimeTable_Plus.this);
                    dialog = builder.setMessage("조회된 강의가 없습니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                }
                adapter.notifyDataSetChanged();
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
                target = "https://cksrudzz.cafe24.com/CourseSearch.php?courseTitle=" + URLEncoder.encode(searchTitle.getText().toString(), "utf-8");
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
                courseList.clear();
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                int courseID;
                String courseYear;
                String courseTerm;
                String courseArea;
                String courseMajor;
                String courseTitle;
                int courseCredit;
                String courseDivide;
                String courseProfessor;
                String courseTime;
                String courseRoom;

                while (count < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(count);
                    courseID = object.getInt("courseID");
                    courseYear = object.getString("courseYear");
                    courseTerm = object.getString("courseTerm");
                    courseArea = object.getString("courseArea");
                    courseMajor = object.getString("courseMajor");
                    courseTitle = object.getString("courseTitle");
                    courseCredit = object.getInt("courseCredit");
                    courseDivide = object.getString("courseDivide");
                    courseProfessor = object.getString("courseProfessor");
                    courseTime = object.getString("courseTime");
                    courseRoom = object.getString("courseRoom");
                    Course course = new Course(courseID, courseYear, courseTerm, courseArea, courseMajor, courseTitle, courseCredit, courseDivide, courseProfessor, courseTime, courseRoom);
                    courseList.add(course);
                    count++;
                }
                if (count == 0) {
                    AlertDialog dialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(TimeTable_Plus.this);
                    dialog = builder.setMessage("조회된 강의가 없습니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                }
                adapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}