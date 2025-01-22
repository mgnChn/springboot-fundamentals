package com.megandemo.springboot_rest_demo.Services;

import com.megandemo.springboot_rest_demo.Models.Topic;
import com.megandemo.springboot_rest_demo.Repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.time.Duration.ofSeconds;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    private List<Topic> topics_collection = new ArrayList<>(Arrays.asList(
            new Topic("spring", "Spring Framework", "Spring Framework Description"),
            new Topic("java", "Core Java", "Core Java Description"),
            new Topic("javascript", "JavaScript", "JavaScript Description")
    ));

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

    public void updateTopic(String topicId, Topic topic){

        topicRepository.save(topic);

        //        Hard-coded list version
//        for(int i = 0; i < topics.size(); ++i){
//            if(topics.get(i).getId().equals(topicId)){
//                topics.set(i, topic);
//                return;
//            }
//        }
    }

    public void deleteTopic(String topicId){

        topicRepository.deleteById(topicId);

        //        Hard-coded list version
//        topics.removeIf(topic -> topic.getId().equals(topicId));
    }

    public List<Topic> transformTopic() {
        List<Topic> topics = new ArrayList<>();

        // flatMap is used to transform each element emitted by Flux/Mono into another Publisher (Flux/Mono)
        // You need to flatten the stream of a streams (Flux<Mono<Topic>> to Flux<Topic>). this way you can process each Topic directly
        Flux<Mono<Topic>> flux = Flux.fromIterable(topics_collection) // returns Flux<Mono<Topic>
                .map(Mono::just);


        // Take each Mono<Topic> within the Flux<Mono<Topic>>,
        // transforming the Topic within each Mono,
        // and then using .subscribe to add each transformed Topic to the topics list.
        flux.flatMap(mono -> mono.map(topic -> {
            topic.setName(topic.getName() + " transformed");
            return topic;
        })).subscribe(topics::add);

        return topics;
    }

    public Flux<Long> constantHotStream() {
        ConnectableFlux<Long> publish = Flux.interval(Duration.ofSeconds(2))
                .publish();

        publish.subscribe(System.out::println);

        publish.connect();
        return publish;
    }
}
