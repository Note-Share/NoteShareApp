package com.example.noteshare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteshare.fragments.PostsFragment;
import com.example.noteshare.fragments.SearchFragment;
import com.example.noteshare.model.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder>{

    public Context context;
    public List<Course> courses;
    private FragmentManager fragmentManager;

    public CourseAdapter(Context context, List<Course> courses, FragmentManager fragmentManager){
        this.context = context;
        this.courses = courses;
        this.fragmentManager = fragmentManager;
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
        public RelativeLayout containerCourse;

        public TextView tvCourseType;
        public TextView tvCourseNumber;
        public TextView tvSectionNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            containerCourse = itemView.findViewById(R.id.containerCourse);

            tvCourseType = itemView.findViewById(R.id.tvCourseType);
            tvCourseNumber = itemView.findViewById(R.id.tvCourseNumber);
            tvSectionNumber = itemView.findViewById(R.id.tvSectionNumber);

        }

        public void bind(Course course) {
            tvCourseType.setText(course.getCourseType());
            tvCourseNumber.setText(course.getCourseNumber());
            tvSectionNumber.setText(course.getSectionNumber());

            containerCourse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Passed in selected course to PostsFragment
                    //PostsFragment renders posts for only selected courses
                    Fragment fragment = new PostsFragment(course);
                    fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                }
            });


        }
    }

}
