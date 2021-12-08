package ict.kosovo.growth.topiccourselessonsapi.repositories;

import ict.kosovo.growth.topiccourselessonsapi.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//me kete kod u be implementimi i CRUD operations ne java spring boot
@Repository
public interface TopicRepository extends JpaRepository<Topic,Integer> {

}
