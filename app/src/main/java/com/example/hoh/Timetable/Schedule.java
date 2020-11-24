package com.example.hoh.Timetable;

import android.content.Context;

import com.example.hoh.R;

public class Schedule {

    private String monday[] = new String[16];
    private String tuesday[] = new String[16];
    private String wednesday[] = new String[16];
    private String thursday[] = new String[16];
    private String friday[] = new String[16];
    private String saturday[] = new String[16];

    public Schedule() {
        for (int i = 0; i < 16; i++) {
            monday[i] = "";
            tuesday[i] = "";
            wednesday[i] = "";
            thursday[i] = "";
            friday[i] = "";
            saturday[i] = "";
        }
    }

    public void clear() {
        for (int i = 0; i < 16; i++) {
            monday[i] = "";
            tuesday[i] = "";
            wednesday[i] = "";
            thursday[i] = "";
            friday[i] = "";
            saturday[i] = "";
        }
    }

    public void addSchedule(String scheduleText) {
        int temp;
        char[] day = new char[10];
        int[] flag = new int[10];
        int check = 0;
        for (int i = 0; i < scheduleText.length(); i++) {
            if (scheduleText.charAt(i) == '월') {
                day[check] = scheduleText.charAt(i);
                flag[check] = i;
                check++;
            }
            if (scheduleText.charAt(i) == '화') {
                day[check] = scheduleText.charAt(i);
                flag[check] = i;
                check++;
            }
            if (scheduleText.charAt(i) == '수') {
                day[check] = scheduleText.charAt(i);
                flag[check] = i;
                check++;
            }
            if (scheduleText.charAt(i) == '목') {
                day[check] = scheduleText.charAt(i);
                flag[check] = i;
                check++;
            }
            if (scheduleText.charAt(i) == '금') {
                day[check] = scheduleText.charAt(i);
                flag[check] = i;
                check++;
            }
            if (scheduleText.charAt(i) == '토') {
                day[check] = scheduleText.charAt(i);
                flag[check] = i;
                check++;
            }
        }
        for (int s = 0; s <= check; s++) {
            if (day[s] == '월') {
                temp = flag[s];
                temp += 2;
                int startPoint = temp;
                int endPoint;
                for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                    if (scheduleText.charAt(i) == '[') {
                        startPoint = i;
                    }
                    if (scheduleText.charAt(i) == ']') {
                        endPoint = i;
                        if (s == 0) {
                            monday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                        } else if (s == 1) {
                            monday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                        } else if (s == 2) {
                            monday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                        } else if (s == 3) {
                            monday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                        }
                    }
                }
            }
            if (day[s] == '화') {
                temp = flag[s];
                temp += 2;
                int startPoint = temp;
                int endPoint;
                for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                    if (scheduleText.charAt(i) == '[') {
                        startPoint = i;
                    }
                    if (scheduleText.charAt(i) == ']') {
                        endPoint = i;
                        if (s == 0) {
                            tuesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                        } else if (s == 1) {
                            tuesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                        } else if (s == 2) {
                            tuesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                        } else if (s == 3) {
                            tuesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                        }
                    }
                }
            }
            if (day[s] == '수') {
                temp = flag[s];
                temp += 2;
                int startPoint = temp;
                int endPoint;
                for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                    if (scheduleText.charAt(i) == '[') {
                        startPoint = i;
                    }
                    if (scheduleText.charAt(i) == ']') {
                        endPoint = i;
                        if (s == 0) {
                            wednesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                        } else if (s == 1) {
                            wednesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                        } else if (s == 2) {
                            wednesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                        } else if (s == 3) {
                            wednesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                        }
                    }
                }
            }
            if (day[s] == '목') {
                temp = flag[s];
                temp += 2;
                int startPoint = temp;
                int endPoint;
                for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                    if (scheduleText.charAt(i) == '[') {
                        startPoint = i;
                    }
                    if (scheduleText.charAt(i) == ']') {
                        endPoint = i;
                        if (s == 0) {
                            thursday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                        } else if (s == 1) {
                            thursday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                        } else if (s == 2) {
                            thursday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                        } else if (s == 3) {
                            thursday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                        }
                    }
                }
            }
            if (day[s] == '금') {
                temp = flag[s];
                temp += 2;
                int startPoint = temp;
                int endPoint;
                for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                    if (scheduleText.charAt(i) == '[') {
                        startPoint = i;
                    }
                    if (scheduleText.charAt(i) == ']') {
                        endPoint = i;
                        if (s == 0) {
                            friday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                        } else if (s == 1) {
                            friday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                        } else if (s == 2) {
                            friday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                        } else if (s == 3) {
                            friday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                        }
                    }
                }
            }
            if (day[s] == '토') {
                temp = flag[s];
                temp += 2;
                int startPoint = temp;
                int endPoint;
                for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                    if (scheduleText.charAt(i) == '[') {
                        startPoint = i;
                    }
                    if (scheduleText.charAt(i) == ']') {
                        endPoint = i;
                        if (s == 0) {
                            saturday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                        } else if (s == 1) {
                            saturday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                        } else if (s == 2) {
                            saturday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                        } else if (s == 3) {
                            saturday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "수업";
                        }
                    }
                }
            }
        }
    }

    public boolean validate(String scheduleText) {
        int a = 0;
        int temp;
        if (scheduleText.equals("")) {
            return true;
        }
        if ((temp = scheduleText.indexOf("월")) > -1) {
            temp += 2;
            int startPoint = temp;
            int endPoint;
            for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                if (scheduleText.charAt(i) == '[') {
                    startPoint = i;
                }
                if (scheduleText.charAt(i) == ']') {
                    endPoint = i;
                    if (!monday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))].equals("")) {
                        a = 1;
                    }
                }
            }
        }
        if ((temp = scheduleText.indexOf("화")) > -1) {
            temp += 2;
            int startPoint = temp;
            int endPoint;
            for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                if (scheduleText.charAt(i) == '[') {
                    startPoint = i;
                }
                if (scheduleText.charAt(i) == ']') {
                    endPoint = i;
                    if (!tuesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))].equals("")) {
                        a = 1;
                    }
                }
            }
        }
        if ((temp = scheduleText.indexOf("수")) > -1) {
            temp += 2;
            int startPoint = temp;
            int endPoint;
            for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                if (scheduleText.charAt(i) == '[') {
                    startPoint = i;
                }
                if (scheduleText.charAt(i) == ']') {
                    endPoint = i;
                    if (!wednesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))].equals("")) {
                        a = 1;
                    }
                }
            }
        }
        if ((temp = scheduleText.indexOf("목")) > -1) {
            temp += 2;
            int startPoint = temp;
            int endPoint;
            for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                if (scheduleText.charAt(i) == '[') {
                    startPoint = i;
                }
                if (scheduleText.charAt(i) == ']') {
                    endPoint = i;
                    if (!thursday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))].equals("")) {
                        a = 1;
                    }
                }
            }
        }
        if ((temp = scheduleText.indexOf("금")) > -1) {
            temp += 2;
            int startPoint = temp;
            int endPoint;
            for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                if (scheduleText.charAt(i) == '[') {
                    startPoint = i;
                }
                if (scheduleText.charAt(i) == ']') {
                    endPoint = i;
                    if (!friday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))].equals("")) {
                        a = 1;
                    }
                }
            }
        }
        if ((temp = scheduleText.indexOf("토")) > -1) {
            temp += 2;
            int startPoint = temp;
            int endPoint;
            for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                if (scheduleText.charAt(i) == '[') {
                    startPoint = i;
                }
                if (scheduleText.charAt(i) == ']') {
                    endPoint = i;
                    if (!saturday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))].equals("")) {
                        a = 1;
                    }

                }
            }
        }
        if (a == 0) {
            return true;
        } else if (a == 1)
            return false;
        else
            return false;
    }

    public void addSchedule(String scheduleText, String courseTitle, String courseProfessor, String courseRoom1, String courseRoom2, String courseRoom3, String courseRoom4) {
        String professor = "";
        String count = scheduleText;
        int temp;
        char[] day = new char[10];
        int[] flag = new int[10];
        int check = 0;
        for (int i = 0; i < scheduleText.length(); i++) {
            if (scheduleText.charAt(i) == '월') {
                day[check] = scheduleText.charAt(i);
                flag[check] = i;
                check++;
            }
            if (scheduleText.charAt(i) == '화') {
                day[check] = scheduleText.charAt(i);
                flag[check] = i;
                check++;
            }
            if (scheduleText.charAt(i) == '수') {
                day[check] = scheduleText.charAt(i);
                flag[check] = i;
                check++;
            }
            if (scheduleText.charAt(i) == '목') {
                day[check] = scheduleText.charAt(i);
                flag[check] = i;
                check++;
            }
            if (scheduleText.charAt(i) == '금') {
                day[check] = scheduleText.charAt(i);
                flag[check] = i;
                check++;
            }
            if (scheduleText.charAt(i) == '토') {
                day[check] = scheduleText.charAt(i);
                flag[check] = i;
                check++;
            }
        }

        for (int s = 0; s <= check; s++) {
            if (day[s] == '월') {
                temp = flag[s];
                temp += 2;
                int startPoint = temp;
                int endPoint;
                for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                    if (scheduleText.charAt(i) == '[') {
                        startPoint = i;
                    }
                    if (scheduleText.charAt(i) == ']') {
                        endPoint = i;
                        if (s == 0) {
                            monday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor + courseRoom1;
                        } else if (s == 1) {
                            monday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor + courseRoom2;
                        } else if (s == 2) {
                            monday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor + courseRoom3;
                        } else if (s == 3) {
                            monday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor + courseRoom4;
                        }
                    }
                }
            }
            if (day[s] == '화') {
                temp = flag[s];
                temp += 2;
                int startPoint = temp;
                int endPoint;
                for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                    if (scheduleText.charAt(i) == '[') {
                        startPoint = i;
                    }
                    if (scheduleText.charAt(i) == ']') {
                        endPoint = i;
                        if (s == 0) {
                            tuesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor + courseRoom1;
                        } else if (s == 1) {
                            tuesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor + courseRoom2;
                        } else if (s == 2) {
                            tuesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor + courseRoom3;
                        } else if (s == 3) {
                            tuesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor + courseRoom4;
                        }
                    }
                }
            }
            if (day[s] == '수') {
                temp = flag[s];
                temp += 2;
                int startPoint = temp;
                int endPoint;
                for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                    if (scheduleText.charAt(i) == '[') {
                        startPoint = i;
                    }
                    if (scheduleText.charAt(i) == ']') {
                        endPoint = i;
                        if (s == 0) {
                            wednesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor + courseRoom1;
                        } else if (s == 1) {
                            wednesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor + courseRoom2;
                        } else if (s == 2) {
                            wednesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor + courseRoom3;
                        } else if (s == 3) {
                            wednesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor + courseRoom4;
                        }
                    }
                }
            }
            if (day[s] == '목') {
                temp = flag[s];
                temp += 2;
                int startPoint = temp;
                int endPoint;
                for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                    if (scheduleText.charAt(i) == '[') {
                        startPoint = i;
                    }
                    if (scheduleText.charAt(i) == ']') {
                        endPoint = i;
                        if (s == 0) {
                            thursday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor + courseRoom1;
                        } else if (s == 1) {
                            thursday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor + courseRoom2;
                        } else if (s == 2) {
                            thursday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor + courseRoom3;
                        } else if (s == 3) {
                            thursday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor + courseRoom4;
                        }
                    }
                }
            }
            if (day[s] == '금') {
                temp = flag[s];
                temp += 2;
                int startPoint = temp;
                int endPoint;
                for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                    if (scheduleText.charAt(i) == '[') {
                        startPoint = i;
                    }
                    if (scheduleText.charAt(i) == ']') {
                        endPoint = i;
                        if (s == 0) {
                            friday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor + courseRoom1;
                        } else if (s == 1) {
                            friday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor + courseRoom2;
                        } else if (s == 2) {
                            friday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor + courseRoom3;
                        } else if (s == 3) {
                            friday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor + courseRoom4;
                        }
                    }
                }
            }
            if (day[s] == '토') {
                temp = flag[s];
                temp += 2;
                int startPoint = temp;
                int endPoint;
                for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                    if (scheduleText.charAt(i) == '[') {
                        startPoint = i;
                    }
                    if (scheduleText.charAt(i) == ']') {
                        endPoint = i;
                        if (s == 0) {
                            saturday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor + courseRoom1;
                        } else if (s == 1) {
                            saturday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor + courseRoom2;
                        } else if (s == 2) {
                            saturday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor + courseRoom3;
                        } else if (s == 3) {
                            saturday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor + courseRoom4;
                        }
                    }
                }
            }
        }


    }

    public void setting(AutoResizeTextView[] monday, AutoResizeTextView[] tuesday, AutoResizeTextView[] wednesday, AutoResizeTextView[] thursday, AutoResizeTextView[] friday, AutoResizeTextView[] saturday, Context context) {
        int maxLength = 0;
        String maxString = "";
        for (int i = 0; i < 16; i++) {
            if (this.monday[i].length() > maxLength) {
                maxLength = this.monday[i].length();

            }
            if (this.tuesday[i].length() > maxLength) {
                maxLength = this.tuesday[i].length();

            }
            if (this.wednesday[i].length() > maxLength) {
                maxLength = this.wednesday[i].length();

            }
            if (this.tuesday[i].length() > maxLength) {
                maxLength = this.tuesday[i].length();

            }
            if (this.friday[i].length() > maxLength) {
                maxLength = this.friday[i].length();

            }
            if (this.saturday[i].length() > maxLength) {
                maxLength = this.saturday[i].length();

            }
        }

        maxString = "                                                                                              ";
        for (int i = 0; i < 16; i++) {
            if (!this.monday[i].equals("")) {
                monday[i].setText(this.monday[i]);
                monday[i].setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            } else {
                monday[i].setText(maxString);
            }
            if (!this.tuesday[i].equals("")) {
                tuesday[i].setText(this.tuesday[i]);
                tuesday[i].setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            } else {
                tuesday[i].setText(maxString);
            }
            if (!this.wednesday[i].equals("")) {
                wednesday[i].setText(this.wednesday[i]);
                wednesday[i].setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            } else {
                wednesday[i].setText(maxString);
            }
            if (!this.thursday[i].equals("")) {
                thursday[i].setText(this.thursday[i]);
                thursday[i].setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            } else {
                thursday[i].setText(maxString);
            }
            if (!this.friday[i].equals("")) {
                friday[i].setText(this.friday[i]);
                friday[i].setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            } else {
                friday[i].setText(maxString);
            }
            if (!this.saturday[i].equals("")) {
                saturday[i].setText(this.saturday[i]);
                saturday[i].setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            } else {
                saturday[i].setText(maxString);
            }
            monday[i].resizeText();
            tuesday[i].resizeText();
            wednesday[i].resizeText();
            thursday[i].resizeText();
            friday[i].resizeText();
            saturday[i].resizeText();
        }
    }
}