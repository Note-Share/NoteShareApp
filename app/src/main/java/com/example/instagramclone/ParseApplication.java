package com.example.instagramclone;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("KYpmdamxtSab39xVBYs5EPNvKbnU4pABqaVpJrPZ")
                .clientKey("0VoqXxbFb9CmpZObDhy4qi5cP1pxgvDLKZLT1Ogm")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
