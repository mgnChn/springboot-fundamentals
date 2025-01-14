package com.megandemo.springboot_rest_demo.Repositories;

import com.megandemo.springboot_rest_demo.Models.Topic;
import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic, String> {

}
