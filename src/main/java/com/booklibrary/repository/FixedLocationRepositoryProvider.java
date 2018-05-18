package com.booklibrary.repository;

import org.springframework.stereotype.Component;

//@Component
public class FixedLocationRepositoryProvider implements RepositoryProvider {

	@Override
	public BookRepository getBookRepository() {
		try {
			return new InMemoryBookRepository(getClass().getClassLoader().getResourceAsStream("com/booklibrary/services/books.json"));
		} catch (InMemoryBookRepositoryInitializationException ex) {
			throw new IllegalStateException(ex);
		}
	}
}
