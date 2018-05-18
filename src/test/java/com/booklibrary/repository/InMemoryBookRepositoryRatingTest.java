package com.booklibrary.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.booklibrary.services.AuthorRating;

public class InMemoryBookRepositoryRatingTest {

	private static BookRepository repository;

	@BeforeClass
	public static void initializeRepository() throws InMemoryBookRepositoryInitializationException {
		String name = "com/booklibrary/services/booksForRating.json";
		repository = new InMemoryBookRepository(InMemoryBookRepository.class.getClassLoader()
				.getResourceAsStream(name));
	}
	
	// 3.25
	@Test
	public void testGetRating() throws InMemoryBookRepositoryInitializationException {
		List<AuthorRating> ratings = repository.getRatings();
		assertThat(ratings).hasSize(1);
		AuthorRating authorRating = ratings.get(0);
		assertThat(authorRating.getAuthor()).isEqualTo("Clifford Geertz");
		assertThat(authorRating.getAverageRating()).isEqualTo(3.25);
	}
	
}
