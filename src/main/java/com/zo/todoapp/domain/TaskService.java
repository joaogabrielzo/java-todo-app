package com.zo.todoapp.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {

  @Autowired
  private TaskRepository repo;

  public List<Task> selectAll() {

    return repo.findAll();
  }

  public Optional<Task> byId(Long id) {

   return repo.findById(id);
  }

  public void save(Task task) {

    repo.save(task);
  }

  public void update(Long id, Task task) {

    Task taskToUpdate = repo.findById(id).get();
    taskToUpdate.setDue_date(task.getDue_date());
    taskToUpdate.setTask(task.getTask());

    repo.save(taskToUpdate);
  }

  public void delete(Long id) {

    repo.deleteById(id);
  }

}
