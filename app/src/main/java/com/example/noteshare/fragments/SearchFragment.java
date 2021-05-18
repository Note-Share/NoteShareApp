package com.example.noteshare.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.noteshare.R;
import com.example.noteshare.model.Course;
import com.example.noteshare.model.Post;
import com.example.noteshare.model.UserCourse;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SearchFragment extends Fragment {
    public static final String TAG = "SearchFragment";
    // EditText Boxes
    private EditText etClassType;
    private EditText etClassNum;
    private EditText etSectionNum;

    private Spinner spinnerSemester;
    private Spinner spinnerYear;

    private String selectedSemester;
    private int selectedYear;

    // Button
    private Button btnAdd;






    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etClassType = view.findViewById(R.id.etClassType);
        etClassNum = view.findViewById(R.id.etClassNum);
        etSectionNum = view.findViewById(R.id.etSectionNum);

        btnAdd = view.findViewById(R.id.btnAdd);

        spinnerSemester = view.findViewById(R.id.spinnerSemester);
        spinnerYear = view.findViewById(R.id.spinnerYear);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Button Clicked:");
                String classType = etClassType.getText().toString();
                String classNum = etClassNum.getText().toString();
                int sectionNum = Integer.parseInt(etSectionNum.getText().toString());


                Log.i(TAG, "Input from the fields: " + classType + "  " + classNum + "  " + sectionNum);
                getCourse(classType, classNum, sectionNum);
            }
        });

        ArrayList<String> semesterOptions = new ArrayList<>();
        semesterOptions.add("Select Semester...");
        semesterOptions.add("Fall");
        semesterOptions.add("Spring");

        ArrayAdapter<String> semesterAdapter = new ArrayAdapter<String>(getContext(),  android.R.layout.simple_spinner_dropdown_item, semesterOptions);
        semesterAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinnerSemester.setAdapter(semesterAdapter);
        spinnerSemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSemester = semesterAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<Integer> yearOptions = new ArrayList<>(Arrays.asList(2021,2020,2019,2018,2017,2016,2015));

        ArrayAdapter<Integer> yearAdapter = new ArrayAdapter<Integer>(getContext(),  android.R.layout.simple_spinner_dropdown_item, yearOptions);
        yearAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(yearAdapter);
        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedYear = yearAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    protected void getCourse(String classType, String classNum, int sectionNum) {
        ParseQuery<Course> query = ParseQuery.getQuery(Course.class);
        // Match input fields to database fields
        query.whereEqualTo(Course.KEY_COURSE_TYPE, classType);
        query.whereEqualTo(Course.KEY_COURSE_NUMBER, classNum);
        query.whereEqualTo(Course.KEY_SECTION_NUMBER, sectionNum);
        query.whereEqualTo(Course.KEY_SEMESTER, selectedSemester);
        query.whereEqualTo(Course.KEY_YEAR, selectedYear);


        query.findInBackground(new FindCallback<Course>() {
            @Override
            public void done(List<Course> courses, ParseException e) {
                if(e != null){
                    Log.e(TAG, "Issues with getting posts", e);
                    Toast.makeText(getContext(), "Course not found", Toast.LENGTH_SHORT).show();
                    return;
                }
                Course courseToAdd;
                // Course Not Found
                if(courses.isEmpty()){
                    Course newCourse = createCourse(classType, classNum, sectionNum);
                    courseToAdd = newCourse;
                    Log.i(TAG, "New Course To Add: " + courseToAdd.getFullCourseName());
                }
                // Course was Found
                else{
                    courseToAdd = courses.get(0);
                    Log.i(TAG, "Course Found: " + courseToAdd.getFullCourseName());
                }
                // Add user to class
                addCourse(courseToAdd);

            }
        });
    }

    protected Course createCourse(String classType, String classNum, int sectionNum){
        Course course = new Course();
        course.setCourseType(classType);
        course.setCourseNumber(classNum);
        course.setSectionNumber(sectionNum);
        course.setSemester(selectedSemester);
        course.setYear(selectedYear);

        course.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null){
                    Log.e(TAG, "Error while saving", e);
                    Toast.makeText(getContext(),"Error while saving",Toast.LENGTH_SHORT).show();
                }
                Log.i(TAG, "Class creation was successful!!");
                Toast.makeText(getContext(),"Class Created!",Toast.LENGTH_SHORT).show();


            }
        });
        return course;
    }


    protected void addCourse(Course course){
        UserCourse userToCourse = new UserCourse();

        userToCourse.setUser(ParseUser.getCurrentUser());
        userToCourse.setCourse(course);


        userToCourse.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null){
                    Log.e(TAG, "Error while saving", e);
                    Toast.makeText(getContext(),"Error while saving",Toast.LENGTH_SHORT).show();
                }
                Log.i(TAG, "Class added was successful!!");
                Toast.makeText(getContext(),"Class was Added!",Toast.LENGTH_SHORT).show();
                etClassType.setText("");
                etClassNum.setText("");
                etSectionNum.setText("");


            }
        });
    }




}