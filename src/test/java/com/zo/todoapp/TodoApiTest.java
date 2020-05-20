package com.zo.todoapp;

import com.zo.todoapp.api.TaskController;
import com.zo.todoapp.domain.Task;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.*;


@SpringBootTest(classes = TodoAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoApiTest {

  @Autowired
  private TaskController rest;

  @Test
  void testGetList() {

    ResponseEntity<List<Task>> tasks = rest.allTasks();
    List<Task> tasksBody = tasks.getBody();

    assertEquals("200 OK", 200, tasks.getStatusCodeValue());
    assertNotNull("Lista não está vazia.", tasksBody);
  }

  @Test
  void testPostTask() {
    Task task = new Task();
    task.setUser("Zó");
    task.setTask("Testar POST");
    task.setDue_date(new java.sql.Date(new java.util.Date().getTime()));

    ResponseEntity postTask = rest.saveTask(task);
    String postBody = String.valueOf(postTask.getHeaders().getLocation());

    assertEquals("201 Created", 201, postTask.getStatusCodeValue());
    assertEquals("Location para a task", "http://localhost:8080/v1/tasks/2", postBody);
  }


}
