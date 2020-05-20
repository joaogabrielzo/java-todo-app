package com.zo.todoapp;

import com.zo.todoapp.domain.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.*;

import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest
class TodoAppApplicationTests {

	@Autowired
	private TaskService service;

	@Test
	void testCRUD() {

//		TaskService service = Mockito.mock(TaskService.class);
		Task task = new Task();
		task.setUser("TestUser");
		task.setTask("Pass all tests");
		task.setDue_date(new java.sql.Date(new java.util.Date().getTime()));

		service.save(task);

		Optional<Task> insertedOne = service.byId(2L);

		assertTrue("Retornou um objeto", insertedOne.isPresent());

		List<Task> tasks = service.selectAll();

		assertEquals("Tem 2 objetos", 2, tasks.size());

		Task taskUpdate = new Task();
		taskUpdate.setUser("TestUser");
		taskUpdate.setTask("Passing another test");

		service.update(2L, taskUpdate);

		Task updatedOne = service.byId(2L).get();

		assertEquals("Objeto foi atualizado", "Passing another test", updatedOne.getTask());

		service.delete(2L);

		Optional<Task> deletedOne = service.byId(2L);

		assertTrue("Objeto foi deletado", deletedOne.isEmpty());

		List<Task> zeroTasks = service.selectAll();

		assertEquals("Tem 1 objeto na tabela", 1, zeroTasks.size());
	}

}
