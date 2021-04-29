package com.example.noteshare.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Course")
public class Course extends ParseObject{

    public static final String KEY_COURSE_ID = "objectId";
    public static final String KEY_COURSE_TYPE = "course_type";
    public static final String KEY_COURSE_NUMBER = "course_number";
    public static final String KEY_SECTION_NUMBER = "section_number";
    public static final String KEY_CREATED = "createdAt";


//    public String getKeyCourseId() {
//        return getString(KEY_COURSE_ID);
//    }

    public String getCourseType() {
        return getString(KEY_COURSE_TYPE);
    }


    public String getCourseNumber() {
        return getString(KEY_COURSE_NUMBER);
    }


    public String getSectionNumber() {
        return getString(KEY_SECTION_NUMBER);
    }

    public String getFullCourseName() {

        return getCourseType() + " - " + getCourseNumber() + " Section " + getSectionNumber();

    }


}
