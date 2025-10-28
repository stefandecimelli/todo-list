package com.decimelli.todo.service;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;

import org.junit.jupiter.api.Test;

import com.decimelli.todo.model.TodoItem;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class TodoItemServiceTest {

	@Test
	public void testAddNewTodoListItem() {
		TodoItem item = new TodoItem();
		item.setName("testAddNewTodoListItem");
		given()
				.contentType("application/json")
				.body(item)
				.when()
				.post("/items")
				.then()
				.statusCode(204);
		int id = given()
				.when()
				.get("/items")
				.then()
				.statusCode(200)
				.body("size()", greaterThan(0))
				.extract()
				.path("[0].id");
		TodoItem newItem = new TodoItem();
		newItem.setName("testAddNewTodoListItemUpdated");
		given()
				.contentType("application/json")
				.body(newItem)
				.when()
				.put("/items/" + id)
				.then()
				.statusCode(204);
		given()
				.when()
				.get("/items")
				.then()
				.statusCode(200)
				.body("[0].name", equalTo("testAddNewTodoListItemUpdated"));
		given()
				.when()
				.delete("/items/" + id)
				.then()
				.statusCode(204);

		given()
				.when()
				.get("/items")
				.then()
				.statusCode(200)
				.body("id", not(hasItem(id)));
	}

}
