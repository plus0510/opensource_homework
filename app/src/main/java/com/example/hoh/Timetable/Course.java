package com.example.hoh.Timetable;

public class Course {

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
    String courseRoom2;
    String courseRoom3;
    String courseRoom4;

    public int getCourseID() {
        return courseID;
    }


    public String getCourseTitle() {
        return courseTitle;
    }


    public int getCourseCredit() {
        return courseCredit;
    }


    public String getCourseDivide() {
        return courseDivide;
    }


    public String getCourseProfessor() {
        return courseProfessor;
    }


    public String getCourseTime() {
        return courseTime;
    }


    public String getCourseRoom() {
        return courseRoom;
    }


    public Course(int courseID, String courseTitle, String courseDivide, String courseProfessor, String courseTime, int courseCredit, String courseRoom, String courseRoom2, String courseRoom3, String courseRoom4) {
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.courseDivide = courseDivide;
        this.courseProfessor = courseProfessor;
        this.courseTime = courseTime;
        this.courseCredit = courseCredit;
        this.courseRoom = courseRoom;
        this.courseRoom2 = courseRoom2;
        this.courseRoom3 = courseRoom3;
        this.courseRoom4 = courseRoom4;
    }

    public Course(int courseID, String courseYear, String courseTerm, String courseArea, String courseMajor, String courseTitle, int courseCredit, String courseDivide, String courseProfessor, String courseTime, String courseRoom) {
        this.courseID = courseID;
        this.courseYear = courseYear;
        this.courseTerm = courseTerm;
        this.courseArea = courseArea;
        this.courseMajor = courseMajor;
        this.courseTitle = courseTitle;
        this.courseCredit = courseCredit;
        this.courseDivide = courseDivide;
        this.courseProfessor = courseProfessor;
        this.courseTime = courseTime;
        this.courseRoom = courseRoom;
    }
}