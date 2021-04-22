package com.example.instragramclone;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Course")
public class Course extends ParseObject{
//    private String courseName;

    public static final String KEY_COURSE_TYPE= "course_type";
    public static final String KEY_COURSE_NUMBER = "course_number";
    public static final String KEY_SECTION_NUMBER = "section_number";
    public static final String KEY_CREATED = "createdAt";



    public String getCourseType() {
        return getString(KEY_COURSE_TYPE);
    }

    public void setCourseType(String courseType){
        put(KEY_COURSE_TYPE, courseType);
    }

    public String getCourseNumber() {
        return getString(KEY_COURSE_NUMBER);
    }

    public void setCourseNumber(String courseNumber){
        put(KEY_COURSE_NUMBER, courseNumber);
    }

    public String getSectionNumber() {
        return getString(KEY_SECTION_NUMBER);
    }

    public void setSectionNumber(String sectionNumber){
        put(KEY_SECTION_NUMBER, sectionNumber);
    }



    //    public String getCourseName() {
//        return courseName;
//    }
//
//    public void setCourseName(String courseName) {
//        this.courseName = courseName;
//    }
}
