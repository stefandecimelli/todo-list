package com.decimelli.todo.service;

import java.util.List;

import com.decimelli.todo.model.TodoItem;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/items")
@ApplicationScoped
public class TodoItemService {

	@Inject
	EntityManager database;

	@POST
	@Transactional
	public Response createItem(TodoItem item) {
		try {
			database.persist(item);
			return Response.accepted().build();
		} catch (RuntimeException e) {
			return Response.serverError().entity(e).build();
		}
	}

	@GET
	public List<TodoItem> getItems() {
		TypedQuery<TodoItem> query = database.createQuery(
				"SELECT t FROM TodoItem t", TodoItem.class);
		return query.getResultList();
	}

	@PUT
	@Path("{id}")
	@Transactional
	public void updateItem(@PathParam("id") Long id, TodoItem item) {
		TodoItem toUpdate = database.find(TodoItem.class, id);
		if (toUpdate != null) {
			toUpdate.setName(item.getName());
		}
	}

	@DELETE
	@Path("{id}")
	@Transactional
	public void deleteItem(@PathParam("id") Long id) {
		TodoItem toDelete = database.find(TodoItem.class, id);
		if (toDelete != null) {
			database.remove(toDelete);
		}
	}

}
