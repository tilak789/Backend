package com.example.model;

import java.util.List;

public class courseDao {

    public List<Course> getCoursesDetails() {
        return coursesDetails;
    }

    public void setCoursesDetails(List<Course> coursesDetails) {
        this.coursesDetails = coursesDetails;
    }

    public List<Course> coursesDetails;
}
