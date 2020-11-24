package com.example.hoh.Timetable;

import android.app.Activity;
import android.content.Context;
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

import org.json.JSONObject;

import java.util.List;

public class StatisticsCourseListAdapter extends BaseAdapter {

    private Context context;
    private List<Course> courseList;
    private Activity parent;
    private String userID = Home.userID;


    public StatisticsCourseListAdapter(Context context, List<Course> courseList, Activity parent) {
        this.context = context;
        this.courseList = courseList;
        this.parent = parent;
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
        View v = View.inflate(context, R.layout.timetable_course_delete, null);
        TextView courseTitle = v.findViewById(R.id.courseTitle);
        TextView courseProfessor = v.findViewById(R.id.courseProfessor);
        TextView courseTime = v.findViewById(R.id.courseTime);
        TextView courseDivide = v.findViewById(R.id.courseDivide);
        TextView courseRoom = v.findViewById(R.id.courseRoom);
        Button deleteButton = v.findViewById(R.id.deleteButton);
        courseTitle.setText(courseList.get(i).getCourseTitle());
        courseDivide.setText(courseList.get(i).getCourseDivide() + "분반");
        if (courseList.get(i).getCourseProfessor().equals("")) {
            courseProfessor.setText("미정");
        } else {
            courseProfessor.setText(courseList.get(i).getCourseProfessor() + "교수님");
        }
        courseTime.setText(courseList.get(i).getCourseTime() + "");
        courseRoom.setText(courseList.get(i).getCourseRoom() + "");

        v.setTag(courseList.get(i).getCourseID());

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(parent);
                                AlertDialog dialog = builder.setMessage("강의가 삭제되었습니다.").setPositiveButton("확인", null).create();
                                dialog.show();
                                Statistics.totalCredit -= courseList.get(i).getCourseCredit();
                                Statistics.credit.setText(Statistics.totalCredit + "학점");
                                courseList.remove(i);
                                notifyDataSetChanged();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(parent);
                                AlertDialog dialog = builder.setMessage("강의 삭제에 실패하였습니다.").setNegativeButton("다시시도", null).create();
                                dialog.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                DeleteRequest deleteRequest = new DeleteRequest(userID, courseList.get(i).getCourseID() + "", responseListener);
                RequestQueue queue = Volley.newRequestQueue(parent);
                queue.add(deleteRequest);
            }

        });


        return v;
    }
}