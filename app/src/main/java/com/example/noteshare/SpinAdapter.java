package com.example.noteshare;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.noteshare.model.Course;

import java.util.List;



public class SpinAdapter extends ArrayAdapter<Course> {

    private Context context;
    private List<Course> courses;


    public SpinAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<Course> courses) {
        super(context, textViewResourceId, courses);

        this.context = context;
        this.courses = courses;
    }

    @Override
    public int getCount(){
        return courses.size();
    }

    @Override
    public Course getItem(int position){
        return courses.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(courses.get(position).getFullCourseName());

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(courses.get(position).getFullCourseName());

        return label;
    }




}
