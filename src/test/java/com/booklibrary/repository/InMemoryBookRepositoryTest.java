package com.booklibrary.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

import com.booklibrary.entities.Item;

public class InMemoryBookRepositoryTest {

	private static BookRepository repository;

	@BeforeClass
	public static void initializeRepository() throws InMemoryBookRepositoryInitializationException {
		String name = "com/booklibrary/services/testBooks.json";
		repository = new InMemoryBookRepository(InMemoryBookRepository.class.getClassLoader()
				.getResourceAsStream(name));
	}

	@Test
	public void testGetbookByIsbn() {
		Item itemByIsbn = repository.getItemByIsbn("9780226285108");
		assertNotNull(itemByIsbn);
		assertEquals("The Religion of Java", itemByIsbn.getVolumeInfo().getTitle());
		assertEquals("SomeSubtitle", itemByIsbn.getVolumeInfo().getSubtitle());
		assertEquals("University of Chicago Press", itemByIsbn.getVolumeInfo().getPublisher());
		assertEquals("SomeDescription", itemByIsbn.getVolumeInfo().getDescription());
		assertEquals((Integer) 392, itemByIsbn.getVolumeInfo().getPageCount());
		assertEquals("http://books.google.com/books/content?id=-SYM4PW-YAgC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
				itemByIsbn.getVolumeInfo().getImageLinks().getThumbnail());
		assertEquals("en", itemByIsbn.getVolumeInfo().getLanguage());
		assertEquals("http://books.google.pl/books?id=-SYM4PW-YAgC&printsec=frontcover&dq=java&hl=&cd=2&source=gbs_api",
				itemByIsbn.getVolumeInfo().getPreviewLink());
		assertEquals(4.0, itemByIsbn.getVolumeInfo().getAverageRating(), 0);
		assertThat(itemByIsbn.getVolumeInfo().getAuthors()).containsExactly("Clifford Geertz");
		assertThat(itemByIsbn.getVolumeInfo().getCategories()).containsExactly("Religion");
	}

	@Test
	public void testGetbookByID() {
		Item itemByIsbn = repository.getItemById("gJEC2q7DzpQC");
		assertNotNull(itemByIsbn);
		assertEquals("The History of Java", itemByIsbn.getVolumeInfo().getTitle());
		assertNull(itemByIsbn.getVolumeInfo().getSubtitle());
		assertNull(itemByIsbn.getVolumeInfo().getDescription());
		assertEquals((Integer) 868, itemByIsbn.getVolumeInfo().getPageCount());
		assertEquals("http://books.google.com/books/content?id=gJEC2q7DzpQC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
				itemByIsbn.getVolumeInfo().getImageLinks().getThumbnail());
		assertEquals("en", itemByIsbn.getVolumeInfo().getLanguage());
		assertEquals("http://books.google.pl/books?id=gJEC2q7DzpQC&printsec=frontcover&dq=java&hl=&cd=3&source=gbs_api",
				itemByIsbn.getVolumeInfo().getPreviewLink());
		assertThat(itemByIsbn.getVolumeInfo().getAuthors()).containsExactly("Sir Thomas Stamford Raffles");
		assertThat(itemByIsbn.getVolumeInfo().getCategories()).containsExactly("Java (Indonesia)");
	}
	
	@Test
	public void testGetBookByCategory() {
		List<Item> itemsByCategory = repository.getItemsByCategory("Computers");
		assertNotNull(itemsByCategory);
		for (Item item : itemsByCategory) {
			assertNotNull(item);
			List<String> categories = item.getVolumeInfo().getCategories();
			assertThat(categories).contains("Computers");
		}
	}
	
	// 3.25
	
}
