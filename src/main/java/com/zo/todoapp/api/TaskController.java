package com.zo.todoapp.api;

import com.zo.todoapp.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

  @Autowired
  TaskService service;

  @GetMapping
  public ResponseEntity<List<Task>> allTasks() {

    return ResponseEntity.ok(service.selectAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Task> byId(@PathVariable("id") Long id) {

    Optional<Task> task = service.byId(id);

    return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Task> saveTask(@RequestBody Task task) {

    service.save(task);
    return ResponseEntity.created(URI.create("http://localhost:8080/v1/tasks/" + task.getId())).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity updateTask(@PathVariable("id") Long id, Task task) {

    try {
      service.update(id, task);
      return ResponseEntity.ok("Task updated");
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteTask(@PathVariable("id") Long id) {

    try {
      service.delete(id);
      return ResponseEntity.ok("Task deleted");
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }
}
