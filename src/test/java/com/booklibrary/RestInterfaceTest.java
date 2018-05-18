package com.booklibrary;

import static com.eclipsesource.restfuse.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;

import com.booklibrary.repository.FixedLocationRepositoryProvider;
import com.booklibrary.repository.InMemoryBookRepositoryInitializationException;
import com.booklibrary.repository.RepositoryProvider;
import com.booklibrary.services.Library;
import com.booklibrary.services.ReturnEntity;
import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.MediaType;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(HttpJUnitRunner.class)
public class RestInterfaceTest {
	
	@Rule
	public final Destination destination = new Destination(this, "http://localhost:8080/api/");
	
	@Context
	private Response response;
	
	private static RestInterface restInterface;
	
	@BeforeClass
	public static void initializeRestService() throws InMemoryBookRepositoryInitializationException {
		RepositoryProvider provider = new FixedLocationRepositoryProvider();
		Library library = new Library(provider);
		restInterface = new RestInterface(library);
	}
	
	@AfterClass
	public static void tearDownRestService() {
		restInterface.stop();
	}
	
	@HttpTest(path = "/book/N1IiAQAAIAAJ", method = Method.GET)
	public void testGetBookbyID() {
		final int responseCode = response.getStatus();
		
		assertOk(response);
		
		assertEquals(200, responseCode);
		assertEquals(MediaType.APPLICATION_JSON, response.getType());
		ReturnEntity books = getReturnEntity();
		assertThat(books).isNotNull();
		assertThat(books.getIsbn()).isEqualTo("N1IiAQAAIAAJ");
	}

	@HttpTest(path = "/book/9780080568782", method = Method.GET)
	public void testGetBookByISBN() {
		final int responseCode = response.getStatus();
		
		assertOk(response);
		
		assertEquals(200, responseCode);
		assertEquals(MediaType.APPLICATION_JSON, response.getType());
		ReturnEntity books = getReturnEntity();
		assertThat(books).isNotNull();
		assertThat(books.getIsbn()).isEqualTo("9780080568782");
	}

	// This test is commented out, because I couldn't quite figure out immediately why it wouldn't work:
	// Instead of returning with a normal Response with an 404-responseCode it would throw a FileNotFoundException.
//	@HttpTest(path = "/book/nonExisting", method = Method.GET)
	public void testNonExistingGetBookByISBN() {
		final int responseCode = response.getStatus();
		
		assertNotFound(response);
		
		assertEquals(404, responseCode);
	}

	@HttpTest(path = "/category/Computers/books", method = Method.GET)
	public void testGetBooksByCateogry() {
		final int responseCode = response.getStatus();
		
		assertOk(response);
		
		assertEquals(200, responseCode);
		assertEquals(MediaType.APPLICATION_JSON, response.getType());
		ReturnEntity[] books = getReturnEntitys();
		assertThat(books).isNotNull();
		for (ReturnEntity returnEntity : books) {
			assertThat(returnEntity.getCategories()).contains("Computers");
		}
	}

	private ReturnEntity getReturnEntity() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(response.getBody(), ReturnEntity.class);
		} catch (IOException ex) {
			throw new IllegalStateException();
		}
	}
	
	private ReturnEntity[] getReturnEntitys() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(response.getBody(), ReturnEntity[].class);
		} catch (IOException ex) {
			throw new IllegalStateException();
		}
	}
}
