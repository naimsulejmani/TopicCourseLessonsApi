package ict.kosovo.growth.topiccourselessonsapi.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
//@Table(name = "topic")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "topicid")
    private  int id;


    @Column(length = 50, unique = true, nullable = false)
    private String name;

    @Column(length = 3000)
    private String description;

    private boolean isActive;

    private LocalDate insertDate;

    private String comment;

    public Topic() {

    }

    public Topic(int id, String name, String description, boolean isActive, LocalDate insertDate,String comment) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.insertDate = insertDate;
        this.comment=comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDate getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(LocalDate insertDate) {
        this.insertDate = insertDate;
    }
}
