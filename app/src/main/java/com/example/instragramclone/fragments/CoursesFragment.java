package com.example.instragramclone.fragments;

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

import com.example.instragramclone.Course;
import com.example.instragramclone.CourseAdapter;
import com.example.instragramclone.Post;
import com.example.instragramclone.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

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

//        allCourses.add(new Course("CMPE-137"));
//        allCourses.add(new Course("CMPE-195B"));
//        allCourses.add(new Course("ENGR-195B"));
//        allCourses.add(new Course("CMPE-188"));

        adapter = new CourseAdapter(getContext(), allCourses);
        rvCourses.setAdapter(adapter);
        rvCourses.setLayoutManager(new LinearLayoutManager(getContext()));

        queryCourses();

    }

    protected void queryCourses() {
        ParseQuery<Course> query = ParseQuery.getQuery(Course.class);
        query.findInBackground(new FindCallback<Course>() {
            @Override
            public void done(List<Course> courses, ParseException e) {
                if(e != null){
                    Log.e(TAG, "Issues with getting posts", e);
                    return;
                }

                allCourses.addAll(courses);
                adapter.notifyDataSetChanged();
            }
        });
    }


}