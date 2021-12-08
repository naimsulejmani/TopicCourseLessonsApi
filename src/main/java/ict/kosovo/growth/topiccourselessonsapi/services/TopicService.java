package ict.kosovo.growth.topiccourselessonsapi.services;

import ict.kosovo.growth.topiccourselessonsapi.exceptions.ConflictExceptions;
import ict.kosovo.growth.topiccourselessonsapi.models.Topic;
import ict.kosovo.growth.topiccourselessonsapi.repositories.TopicRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    //@Autowired
    private final TopicRepository repository;

    public TopicService(TopicRepository repository) {
        this.repository = repository;
    }

    public List<Topic> getTopics() {
        return  this.repository.findAll();
    }


    public Topic getTopicById(int topicId) throws ChangeSetPersister.NotFoundException {
        Optional<Topic> topic = repository.findById(topicId);
        if(topic.isPresent())
            return topic.get();

        throw new ChangeSetPersister.NotFoundException();
       // return null;

        //return topic.orElse(null);
    }


    public Topic addTopic(Topic topic) throws ConflictExceptions{
        try {
            Topic oldTopic = getTopicById(topic.getId());
            if(oldTopic!=null) throw new ConflictExceptions("Topic already exists!!!");
        } catch (ChangeSetPersister.NotFoundException e) {

        }
        Topic newTopic = this.repository.save(topic);
        return newTopic;
    }


    public Topic updateTopic(Topic topic) throws ChangeSetPersister.NotFoundException {
        Topic oldTopic = getTopicById(topic.getId());
        Topic updatedTopic = this.repository.save(topic);
        return updatedTopic;
    }

    public Topic deleteTopic(int topicId) throws ChangeSetPersister.NotFoundException {
        Topic deletedTopic =getTopicById(topicId);
        this.repository.delete(deletedTopic);
        return deletedTopic;
    }
}
