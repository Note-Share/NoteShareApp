package com.example.noteshare.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Course")
public class Course extends ParseObject{

    public static final String KEY_COURSE_ID = "objectId";
    public static final String KEY_COURSE_TYPE = "course_type";
    public static final String KEY_COURSE_NUMBER = "course_number";
    public static final String KEY_SECTION_NUMBER = "section_number";
    public static final String KEY_SEMESTER = "semester";
    public static final String KEY_YEAR = "year";


    public static final String KEY_CREATED = "createdAt";


//    public String getKeyCourseId() {
//        return getString(KEY_COURSE_ID);
//    }

    public String getCourseType() {
        return getString(KEY_COURSE_TYPE);
    }

    public void setCourseType(String courseType) {
        put(KEY_COURSE_TYPE, courseType);
    }


    public String getCourseNumber() {
        return getString(KEY_COURSE_NUMBER);
    }

    public void setCourseNumber(String courseNumber) {
        put(KEY_COURSE_NUMBER, courseNumber);
    }


    public int getSectionNumber() {
        return getInt(KEY_SECTION_NUMBER);
    }

    public void setSectionNumber(int sectionNumber) {
        put(KEY_SECTION_NUMBER, sectionNumber);
    }

    public String getSemester() {
        return getString(KEY_SEMESTER);
    }

    public void setSemester(String semester) {
        put(KEY_SEMESTER, semester);
    }

    public int getYear() {
        return getInt(KEY_YEAR);
    }

    public void setYear(int year) {
        put(KEY_YEAR, year);
    }



    public String getFullCourseName() {

        return getCourseType() + " - " + getCourseNumber() + " Section " + getSectionNumber();

    }

    public String getCourseTitle() {

        return getCourseType() + " - " + getCourseNumber() + "  Notes" ;

    }




}
