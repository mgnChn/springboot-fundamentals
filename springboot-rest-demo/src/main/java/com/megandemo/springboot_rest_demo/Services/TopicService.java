package com.megandemo.springboot_rest_demo.Services;

import com.megandemo.springboot_rest_demo.Models.TopicModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TopicService {

    private List<TopicModel> topics = new ArrayList<>(Arrays.asList(
            new TopicModel("spring", "Spring Framework", "Spring Framework Description"),
            new TopicModel("java", "Core Java", "Core Java Description"),
            new TopicModel("javascript", "JavaScript", "JavaScript Description")
    ));

    public List<TopicModel> getTopics() {
        return topics;
    }

    public TopicModel getTopic(String id){
        return topics.stream().filter(topic -> topic.getId().equals(id)).findFirst().orElse(null);
    }

    public void addTopic(TopicModel topicModel){
        topics.add(topicModel);
    }

    public void updateTopic(String topicId, TopicModel topicModel){
        for(int i = 0; i < topics.size(); ++i){
            if(topics.get(i).getId().equals(topicId)){
                topics.set(i, topicModel);
                return;
            }
        }
    }

    public void deleteTopic(String topicId){
        topics.removeIf(topic -> topic.getId().equals(topicId));
    }
}
