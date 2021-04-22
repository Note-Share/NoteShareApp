package com.example.noteshare.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteshare.model.Course;
import com.example.noteshare.CourseAdapter;
import com.example.noteshare.R;
import com.example.noteshare.model.Post;
import com.example.noteshare.model.UserCourse;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class CoursesFragment extends Fragment {

    public static final String TAG = "CourseFragment";

    // TODO: Rename and change types of parameters
    private RecyclerView rvCourses;
    protected CourseAdapter adapter;
    protected List<Course> allCourses;

    public CoursesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_courses, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Setup Recycler View
        rvCourses = view.findViewById(R.id.rvCourses);
        allCourses = new ArrayList<>();

        adapter = new CourseAdapter(getContext(), allCourses);
        rvCourses.setAdapter(adapter);
        rvCourses.setLayoutManager(new LinearLayoutManager(getContext()));

        queryCourses();

    }

    protected void queryCourses() {
        ParseQuery<UserCourse> query = ParseQuery.getQuery(UserCourse.class);
        // Extra Query Options
        query.include(UserCourse.KEY_COURSE);
        query.whereEqualTo(UserCourse.KEY_USER, ParseUser.getCurrentUser());


        query.findInBackground(new FindCallback<UserCourse>() {
            @Override
            public void done(List<UserCourse> userCourses, ParseException e) {
                if(e != null){
                    Log.e(TAG, "Issues with getting posts", e);
                    return;
                }
                ArrayList<Course> courses = new ArrayList<>();

                for(UserCourse userCourse: userCourses){
                    courses.add((Course)userCourse.getCourse());
                }

                allCourses.addAll(courses);
                adapter.notifyDataSetChanged();
            }
        });
    }


}