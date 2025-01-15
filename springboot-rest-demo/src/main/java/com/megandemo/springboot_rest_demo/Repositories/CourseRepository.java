package com.megandemo.springboot_rest_demo.Repositories;

import com.megandemo.springboot_rest_demo.Models.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, String> {

    public List<Course> findByTopicId(String topicId);
}
