package com.booklibrary.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.booklibrary.entities.Item;
import com.booklibrary.repository.BookRepository;
import com.booklibrary.repository.InMemoryBookRepository;

@Path("/api/")
public class Library {
	
	private BookRepository repository;
	
	public Library() {
		super();
		// TODO Konfigurierbar
		this.repository = new InMemoryBookRepository(getClass().getResourceAsStream("books.json"));
	}

	@GET
	@Path("/book/{isbn}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBookByIsbn(@PathParam("isbn") final String isbn) {
		Item item = repository.getItemByIsbn(isbn);
		if (item == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		ReturnEntity entity = new ItemToReturnEntityTranslator(item).translate();
		return Response.status(Response.Status.OK).entity(entity).build();
	}
	
	@GET
	@Path("/category/{categoryName}/books")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBooksByCategory(@PathParam("categoryName") String categoryName) {
		List<Item> item = repository.getItemsByCategory(categoryName);
		if (item == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		List<ReturnEntity> returnValues = new ArrayList<>();
		item.forEach(c -> returnValues.add(new ItemToReturnEntityTranslator(c).translate()));
		return Response.status(Response.Status.OK).entity(returnValues).build();
	}
	
	@GET
	@Path("/rating")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRating() {
		List<AuthorRating> item = repository.getRatings();
		if (item == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.status(Response.Status.OK).entity(item).build();
	}
}
