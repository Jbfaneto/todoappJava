package com.app.toDoList.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;


@Entity
@Table(name = "to_do_item")
public class ToDoItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long id;

    @Getter
    @Setter
    private String description;

    @Setter
    @Getter
    private boolean complete;

    @Setter
    @Getter
    private Instant createdDate;

    public ToDoItem() {
    }

    public ToDoItem(String description) {
        this.description = description;
        this.complete = false;
        this.createdDate = Instant.now();
    }


}
