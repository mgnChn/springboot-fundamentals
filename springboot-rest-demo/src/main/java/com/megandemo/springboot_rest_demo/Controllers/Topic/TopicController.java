package com.megandemo.springboot_rest_demo.Controllers.Topic;

import com.megandemo.springboot_rest_demo.Services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.megandemo.springboot_rest_demo.Models.TopicModel;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;

    @RequestMapping("/topics")
    public List<TopicModel> getAllTopics(){

        // Create a TopicModel fixed list (Returning an ArrayList is dynamic but takes more room)
        return topicService.getTopics();

    }

    @RequestMapping("/topics/{topicId}")
    public TopicModel getTopic(@PathVariable("topicId") String topicId){

        // Create a TopicModel fixed list (Returning an ArrayList is dynamic but takes more room)
        return topicService.getTopic(topicId);

    }

    @PostMapping("/topics")
    public void addTopic(@RequestBody TopicModel topicModel){
        topicService.addTopic(topicModel);
    }

    @PutMapping("/topics/{topicId}")
    public void updateTopic(@PathVariable("topicId") String topicId, @RequestBody TopicModel topicModel){
        topicService.updateTopic(topicId, topicModel);
    }

    @DeleteMapping("/topics/{topicId}")
    public void deleteTopic(@PathVariable("topicId") String topicId){
        topicService.deleteTopic(topicId);
    }
}
