package ict.kosovo.growth.topiccourselessonsapi.controllers;

import ict.kosovo.growth.topiccourselessonsapi.exceptions.ConflictExceptions;
import ict.kosovo.growth.topiccourselessonsapi.models.Topic;
import ict.kosovo.growth.topiccourselessonsapi.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "topics")
public class TopicController {

    private final TopicService service;

    public TopicController(TopicService service) {
        this.service = service;
    }

    //http://localhost:9090/topics
    @GetMapping(value = "")
    public List<Topic> getAll() {
        return this.service.getTopics();
    }

    //http://localhost:9090/topics/1
    //vendosja e parametrave (PathVariable)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Topic> getById(@PathVariable int id) {
        try {
            Topic topic = this.service.getTopicById(id);
            return ResponseEntity.status(HttpStatus.OK).body(topic);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<Topic> add(@RequestBody Topic topic) {
        try {
            Topic newTopic = this.service.addTopic(topic);
            return ResponseEntity.status(HttpStatus.CREATED).body(newTopic);
        } catch (ConflictExceptions e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(topic);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Topic> update(@PathVariable int id, @RequestBody Topic topic) {
        if (topic != null && topic.getId() == id) {
            Topic updatedTopic = null;
            try {
                updatedTopic = this.service.updateTopic(topic);
                return ResponseEntity.status(HttpStatus.OK).body(updatedTopic);
            } catch (ChangeSetPersister.NotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(topic);
            }

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Topic> delete(@PathVariable int id) {
        try {
            Topic deletedTopic =this.service.deleteTopic(id);
            return ResponseEntity.status(HttpStatus.OK).body(deletedTopic);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }


}
