package com.example.noteshare.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("UserCourse")
public class UserCourse extends ParseObject {

    public static final String KEY_USER = "user";
    public static final String KEY_COURSE = "course";

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public ParseObject getCourse() {
        return getParseObject(KEY_COURSE);
    }


}
