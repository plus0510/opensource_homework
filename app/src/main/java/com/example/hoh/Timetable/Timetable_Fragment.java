package com.example.hoh.Timetable;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hoh.Login.LoginActivity;
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

public class Timetable_Fragment extends Fragment {
    ViewGroup viewGroup;
    ImageView add;
    ImageView remove;

    @Nullable

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
    }

    private AutoResizeTextView monday[] = new AutoResizeTextView[16];
    private AutoResizeTextView tuesday[] = new AutoResizeTextView[16];
    private AutoResizeTextView wednesday[] = new AutoResizeTextView[16];
    private AutoResizeTextView thursday[] = new AutoResizeTextView[16];
    private AutoResizeTextView friday[] = new AutoResizeTextView[16];
    private AutoResizeTextView saturday[] = new AutoResizeTextView[16];
    private Schedule schedule = new Schedule();

    @Override
    public void onActivityCreated(Bundle b) {
        super.onActivityCreated(b);

    }

    @Override
    public void onResume() {
        super.onResume();
        create();
        new BackgroundTask().execute();
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target2;

        @Override
        protected void onPreExecute() {
            try {
                target2 = "https://cksrudzz.cafe24.com/ScheduleList.php?userID=" + URLEncoder.encode(((LoginActivity) LoginActivity.context_main).userID, "UTF-8");
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
                schedule.clear();
                String courseProfessor;
                String courseTitle;
                String courseTime;
                String courseRoom1;
                String courseRoom2;
                String courseRoom3;
                String courseRoom4;
                while (count < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(count);
                    courseTime = object.getString("courseTime");
                    courseProfessor = object.getString("courseProfessor");
                    courseTitle = object.getString("courseTitle");
                    courseRoom1 = object.getString("courseRoom");
                    courseRoom2 = object.getString("courseRoom2");
                    courseRoom3 = object.getString("courseRoom3");
                    courseRoom4 = object.getString("courseRoom4");
                    schedule.addSchedule(courseTime, courseTitle, courseProfessor, courseRoom1, courseRoom2, courseRoom3, courseRoom4);
                    count++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 16; i++) {
                monday[i].setText("");
                tuesday[i].setText("");
                wednesday[i].setText("");
                thursday[i].setText("");
                friday[i].setText("");
                saturday[i].setText("");
            }
            schedule.setting(monday, tuesday, wednesday, thursday, friday, saturday, getContext());
        }
    }

    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.timetable_fragment, container, false);
        add = viewGroup.findViewById(R.id.TimeTable_Plus_Button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(getActivity(), TimeTable_Plus.class);
                Timetable_Fragment.this.startActivity(addIntent);
            }
        });
        remove = viewGroup.findViewById(R.id.TimeTable_remove_Button);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent removeIntent = new Intent(getActivity(), Statistics.class);
                Timetable_Fragment.this.startActivity(removeIntent);
            }
        });
        return viewGroup;
    }

    public void create() {
        monday[0] = getView().findViewById(R.id.monday0);
        monday[1] = getView().findViewById(R.id.monday1);
        monday[2] = getView().findViewById(R.id.monday2);
        monday[3] = getView().findViewById(R.id.monday3);
        monday[4] = getView().findViewById(R.id.monday4);
        monday[5] = getView().findViewById(R.id.monday5);
        monday[6] = getView().findViewById(R.id.monday6);
        monday[7] = getView().findViewById(R.id.monday7);
        monday[8] = getView().findViewById(R.id.monday8);
        monday[9] = getView().findViewById(R.id.monday9);
        monday[10] = getView().findViewById(R.id.monday10);
        monday[11] = getView().findViewById(R.id.monday11);
        monday[12] = getView().findViewById(R.id.monday12);
        monday[13] = getView().findViewById(R.id.monday13);
        monday[14] = getView().findViewById(R.id.monday14);
        monday[15] = getView().findViewById(R.id.monday15);

        tuesday[0] = getView().findViewById(R.id.thursday0);
        tuesday[1] = getView().findViewById(R.id.tuesday1);
        tuesday[2] = getView().findViewById(R.id.tuesday2);
        tuesday[3] = getView().findViewById(R.id.tuesday3);
        tuesday[4] = getView().findViewById(R.id.tuesday4);
        tuesday[5] = getView().findViewById(R.id.tuesday5);
        tuesday[6] = getView().findViewById(R.id.tuesday6);
        tuesday[7] = getView().findViewById(R.id.tuesday7);
        tuesday[8] = getView().findViewById(R.id.tuesday8);
        tuesday[9] = getView().findViewById(R.id.tuesday9);
        tuesday[10] = getView().findViewById(R.id.tuesday10);
        tuesday[11] = getView().findViewById(R.id.tuesday11);
        tuesday[12] = getView().findViewById(R.id.tuesday12);
        tuesday[13] = getView().findViewById(R.id.tuesday13);
        tuesday[14] = getView().findViewById(R.id.tuesday14);
        tuesday[15] = getView().findViewById(R.id.tuesday15);

        wednesday[0] = getView().findViewById(R.id.wednesday0);
        wednesday[1] = getView().findViewById(R.id.wednesday1);
        wednesday[2] = getView().findViewById(R.id.wednesday2);
        wednesday[3] = getView().findViewById(R.id.wednesday3);
        wednesday[4] = getView().findViewById(R.id.wednesday4);
        wednesday[5] = getView().findViewById(R.id.wednesday5);
        wednesday[6] = getView().findViewById(R.id.wednesday6);
        wednesday[7] = getView().findViewById(R.id.wednesday7);
        wednesday[8] = getView().findViewById(R.id.wednesday8);
        wednesday[9] = getView().findViewById(R.id.wednesday9);
        wednesday[10] = getView().findViewById(R.id.wednesday10);
        wednesday[11] = getView().findViewById(R.id.wednesday11);
        wednesday[12] = getView().findViewById(R.id.wednesday12);
        wednesday[13] = getView().findViewById(R.id.wednesday13);
        wednesday[14] = getView().findViewById(R.id.wednesday14);
        wednesday[15] = getView().findViewById(R.id.wednesday15);

        thursday[0] = getView().findViewById(R.id.thursday0);
        thursday[1] = getView().findViewById(R.id.thursday1);
        thursday[2] = getView().findViewById(R.id.thursday2);
        thursday[3] = getView().findViewById(R.id.thursday3);
        thursday[4] = getView().findViewById(R.id.thursday4);
        thursday[5] = getView().findViewById(R.id.thursday5);
        thursday[6] = getView().findViewById(R.id.thursday6);
        thursday[7] = getView().findViewById(R.id.thursday7);
        thursday[8] = getView().findViewById(R.id.thursday8);
        thursday[9] = getView().findViewById(R.id.thursday9);
        thursday[10] = getView().findViewById(R.id.thursday10);
        thursday[11] = getView().findViewById(R.id.thursday11);
        thursday[12] = getView().findViewById(R.id.thursday12);
        thursday[13] = getView().findViewById(R.id.thursday13);
        thursday[14] = getView().findViewById(R.id.thursday14);
        thursday[15] = getView().findViewById(R.id.thursday15);

        friday[0] = getView().findViewById(R.id.friday0);
        friday[1] = getView().findViewById(R.id.friday1);
        friday[2] = getView().findViewById(R.id.friday2);
        friday[3] = getView().findViewById(R.id.friday3);
        friday[4] = getView().findViewById(R.id.friday4);
        friday[5] = getView().findViewById(R.id.friday5);
        friday[6] = getView().findViewById(R.id.friday6);
        friday[7] = getView().findViewById(R.id.friday7);
        friday[8] = getView().findViewById(R.id.friday8);
        friday[9] = getView().findViewById(R.id.friday9);
        friday[10] = getView().findViewById(R.id.friday10);
        friday[11] = getView().findViewById(R.id.friday11);
        friday[12] = getView().findViewById(R.id.friday12);
        friday[13] = getView().findViewById(R.id.friday13);
        friday[14] = getView().findViewById(R.id.friday14);
        friday[15] = getView().findViewById(R.id.friday15);

        saturday[0] = getView().findViewById(R.id.saturday0);
        saturday[1] = getView().findViewById(R.id.saturday1);
        saturday[2] = getView().findViewById(R.id.saturday2);
        saturday[3] = getView().findViewById(R.id.saturday3);
        saturday[4] = getView().findViewById(R.id.saturday4);
        saturday[5] = getView().findViewById(R.id.saturday5);
        saturday[6] = getView().findViewById(R.id.saturday6);
        saturday[7] = getView().findViewById(R.id.saturday7);
        saturday[8] = getView().findViewById(R.id.saturday8);
        saturday[9] = getView().findViewById(R.id.saturday9);
        saturday[10] = getView().findViewById(R.id.saturday10);
        saturday[11] = getView().findViewById(R.id.saturday11);
        saturday[12] = getView().findViewById(R.id.saturday12);
        saturday[13] = getView().findViewById(R.id.saturday13);
        saturday[14] = getView().findViewById(R.id.saturday14);
        saturday[15] = getView().findViewById(R.id.saturday15);
    }
}