package com.example.instragramclone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder>{
    public Context context;
    public List<Course> courses;

    public CourseAdapter(Context context, List<Course> courses){
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View CourseView = LayoutInflater.from(context).inflate(R.layout.item_course, parent, false);
        return new ViewHolder(CourseView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.bind(course);
    }

    @Override
    public int getItemCount() {

        return courses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCourse;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCourse = itemView.findViewById(R.id.tvCourse);
        }

        public void bind(Course course) {
            tvCourse.setText(course.getCourseType());
        }
    }

}
