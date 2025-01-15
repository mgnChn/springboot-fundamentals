package com.megandemo.springboot_rest_demo.Controllers;

import com.megandemo.springboot_rest_demo.Models.Course;
import com.megandemo.springboot_rest_demo.Models.Topic;
import com.megandemo.springboot_rest_demo.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/topics/{topicId}/courses")
    public List<Course> getAllCourses(@PathVariable("topicId") String topicId){

        return courseService.getCourses(topicId);
    }

    @RequestMapping("/topics/{topicId}/courses/{courseId}")
    public Course getCourse(@PathVariable("courseId") String courseId){

        return courseService.getCourse(courseId);
    }

    @PostMapping("/topics/{topicId}/courses")
    public void addCourse(@PathVariable("topicId") String topicId, @RequestBody Course course){
        // Set the topic for the course
        course.setTopic(new Topic(topicId, "", ""));
        courseService.addCourse(course);
    }

    @PutMapping("/topics/{topicId}/courses/{courseId}")
    public void updateCourse(@PathVariable("topicId") String topicId, @PathVariable("courseId") String courseId, @RequestBody Course course){
        course.setTopic(new Topic(topicId, "", ""));
        courseService.updateCourse(course);
    }

    @DeleteMapping("/topics/{topicId}/courses/{courseId}")
    public void deleteCourse( @PathVariable("courseId") String courseId){
        courseService.deleteCourse(courseId);
    }
}
