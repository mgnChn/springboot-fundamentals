package com.megandemo.springboot_rest_demo.Controllers;

import com.megandemo.springboot_rest_demo.Services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.megandemo.springboot_rest_demo.Models.Topic;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;

    @RequestMapping("/topics")
    public List<Topic> getAllTopics(){

        // Create a Topic fixed list (Returning an ArrayList is dynamic but takes more room)
        return topicService.getTopics();

    }

    @RequestMapping("/topics/{topicId}")
    public Topic getTopic(@PathVariable("topicId") String topicId){

        // Create a Topic fixed list (Returning an ArrayList is dynamic but takes more room)
        return topicService.getTopic(topicId);

    }

    @PostMapping("/topics")
    public void addTopic(@RequestBody Topic topicModel){
        topicService.addTopic(topicModel);
    }

    @PutMapping("/topics/{topicId}")
    public void updateTopic(@PathVariable("topicId") String topicId, @RequestBody Topic topicModel){
        topicService.updateTopic(topicId, topicModel);
    }

    @DeleteMapping("/topics/{topicId}")
    public void deleteTopic(@PathVariable("topicId") String topicId){
        topicService.deleteTopic(topicId);
    }

    @GetMapping("/topics/transformtopic")
    public List<Topic> transformTopic(){
        return topicService.transformTopic();
    }

    @GetMapping(value = "/topics/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Long> streamTopic() {
        return topicService.constantHotStream();
    }
}
