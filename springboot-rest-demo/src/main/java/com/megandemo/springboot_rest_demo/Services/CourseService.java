package com.megandemo.springboot_rest_demo.Services;

import com.megandemo.springboot_rest_demo.Models.Course;
import com.megandemo.springboot_rest_demo.Repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getCourses(String topicId) {

        List<Course> courses = new ArrayList<>();
        courseRepository.findByTopicId(topicId)
                .forEach(courses::add);

        return courses;
    }

    public Course getCourse(String id){
        return courseRepository.findById(id).orElse(null);
    }

    public void addCourse(Course course){

        courseRepository.save(course);
    }

    public void updateCourse(Course course){

        courseRepository.save(course);
    }

    public void deleteCourse(String courseId){

        courseRepository.deleteById(courseId);
    }
}
