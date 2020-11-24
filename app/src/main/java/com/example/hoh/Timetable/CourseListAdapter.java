package com.example.hoh.Timetable;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
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

public class CourseListAdapter extends BaseAdapter {

    private Context context;
    private List<Course> courseList;
    private Activity parent;
    private String userID = Home.userID;
    private Schedule schedule;
    private List<Integer> courseIDList;


    public CourseListAdapter(Context context, List<Course> courseList, Activity parent) {
        this.context = context;
        this.courseList = courseList;
        this.parent = parent;
        schedule = new Schedule();
        courseIDList = new ArrayList<Integer>();
        new BackgroundTask().execute();
    }

    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public Object getItem(int i) {
        return courseList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.timetable_course_add, null);
        TextView courseTitle = v.findViewById(R.id.courseTitle);
        TextView courseCredit = v.findViewById(R.id.courseCredit);
        TextView courseDivide = v.findViewById(R.id.courseDivide);
        TextView courseProfessor = v.findViewById(R.id.courseProfessor);
        TextView courseTime = v.findViewById(R.id.courseTime);
        TextView courseRoom = v.findViewById(R.id.courseRoom);
        Button addButton = v.findViewById(R.id.addButton);
        courseTitle.setText(courseList.get(i).getCourseTitle());
        courseCredit.setText(courseList.get(i).getCourseCredit() + "학점");
        courseDivide.setText(courseList.get(i).getCourseDivide() + "분반");
        if (courseList.get(i).getCourseProfessor().equals("")) {
            courseProfessor.setText("미정");
        } else {
            courseProfessor.setText(courseList.get(i).getCourseProfessor() + "교수님");
        }
        courseTime.setText(courseList.get(i).getCourseTime() + "");
        courseRoom.setText(courseList.get(i).getCourseRoom() + "");

        v.setTag(courseList.get(i).getCourseID());


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean validate;
                validate = schedule.validate(courseList.get(i).getCourseTime());
                if (!alreadyIn(courseIDList, courseList.get(i).getCourseID())) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(parent);
                    AlertDialog dialog = builder.setMessage("이미 추가한 강의입니다.").setPositiveButton("다시시도", null).create();
                    dialog.show();
                } else if (validate == false) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(parent);
                    AlertDialog dialog = builder.setMessage("시간표가 중복됩니다.").setPositiveButton("다시시도", null).create();
                    dialog.show();
                } else {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");
                                if (success) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(parent);
                                    AlertDialog dialog = builder.setMessage("강의가 추가되었습니다.").setPositiveButton("확인", null).create();
                                    dialog.show();
                                    courseIDList.add(courseList.get(i).getCourseID());
                                    schedule.addSchedule(courseList.get(i).getCourseTime());
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(parent);
                                    AlertDialog dialog = builder.setMessage("강의 추가에 실패하였습니다.").setNegativeButton("확인", null).create();
                                    dialog.show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    AddRequest addRequest = new AddRequest(userID, courseList.get(i).getCourseID() + "", responseListener);
                    RequestQueue queue = Volley.newRequestQueue(parent);
                    queue.add(addRequest);
                }
            }
        });
        return v;
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            try {
                target = "https://cksrudzz.cafe24.com/ScheduleList.php?userID=" + URLEncoder.encode(userID, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
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
                String courseTime;
                int courseID;
                while (count < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(count);
                    courseID = object.getInt("courseID");
                    courseTime = object.getString("courseTime");
                    courseIDList.add(courseID);
                    schedule.addSchedule(courseTime);
                    count++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean alreadyIn(List<Integer> courseIDList, int item) {
        for (int i = 0; i < courseIDList.size(); i++) {
            if (courseIDList.get(i) == item) {
                return false;
            }
        }
        return true;
    }
}
