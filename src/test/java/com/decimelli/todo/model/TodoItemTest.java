package com.decimelli.todo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoItemTest {

	@Test
	public void testTodoItem() {
		TodoItem test = new TodoItem();
		test.setName("name1");
		test.setId(1L);

		assertEquals("name1", test.getName());
		assertEquals(1, test.getId());
		assertEquals(TodoItem.class, test.getClass());
	}

}
