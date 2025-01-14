package com.megandemo.springboot_rest_demo.Services;

import com.megandemo.springboot_rest_demo.Models.Topic;
import com.megandemo.springboot_rest_demo.Repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

//    private List<Topic> topics = new ArrayList<>(Arrays.asList(
//            new Topic("spring", "Spring Framework", "Spring Framework Description"),
//            new Topic("java", "Core Java", "Core Java Description"),
//            new Topic("javascript", "JavaScript", "JavaScript Description")
//    ));

    public List<Topic> getTopics() {

        List<Topic> topics = new ArrayList<>();
        topicRepository.findAll()
                .forEach(topics::add);

        return topics;

//        Hard-coded list version
//        return topics;
    }

    public Topic getTopic(String id){
        return getTopics().stream().filter(topic -> topic.getId().equals(id)).findFirst().orElse(null);
    }

    public void addTopic(Topic topic){

        topicRepository.save(topic);

//        Hard-coded list version
//        topics.add(topicModel);
    }

//    public void updateTopic(String topicId, Topic topicModel){
//        for(int i = 0; i < topics.size(); ++i){
//            if(topics.get(i).getId().equals(topicId)){
//                topics.set(i, topicModel);
//                return;
//            }
//        }
//    }
//
//    public void deleteTopic(String topicId){
//        topics.removeIf(topic -> topic.getId().equals(topicId));
//    }
}
