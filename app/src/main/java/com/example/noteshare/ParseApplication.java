package com.example.noteshare;

import android.app.Application;

import com.example.noteshare.model.Course;
import com.example.noteshare.model.Post;
import com.example.noteshare.model.UserCourse;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Register your parse models
        ParseObject.registerSubclass(Post.class);
        ParseObject.registerSubclass(Course.class);
        ParseObject.registerSubclass(UserCourse.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("04aKn3WBi2FP8PydFwkC89SpCmt4xhS8dGllLj6y")
                .clientKey("aUQEVuog25n9Vfr3s0dLgYjfCfyYp5cfGzJZJeEo")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
