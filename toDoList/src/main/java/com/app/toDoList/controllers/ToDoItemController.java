package com.app.toDoList.controllers;

import com.app.toDoList.models.ToDoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.toDoList.repositories.ToDoItemRepository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/toDoItems")
public class ToDoItemController {
    @Autowired
    private ToDoItemRepository toDOItemRepository;

    @GetMapping("/all")
    public List<ToDoItem> findAll() {
        return (List<ToDoItem>) toDOItemRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoItem> findById(@PathVariable Long id) {
        return toDOItemRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<ToDoItem> create(@RequestBody ToDoItem toDoItem) {
        ToDoItem createdItem = toDOItemRepository.save(toDoItem);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdItem.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdItem);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ToDoItem> update(@PathVariable("id") Long id, @RequestBody ToDoItem toDoItem) {
        return toDOItemRepository.findById(id)
                .map(record -> {
                    record.setDescription(toDoItem.getDescription());
                    record.setComplete(toDoItem.isComplete());
                    ToDoItem updated = toDOItemRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }
}
