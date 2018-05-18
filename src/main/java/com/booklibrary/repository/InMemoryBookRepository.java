package com.booklibrary.repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.booklibrary.entities.Books;
import com.booklibrary.entities.IndustryIdentifier;
import com.booklibrary.entities.Item;
import com.booklibrary.entities.VolumeInfo;
import com.booklibrary.services.AuthorRating;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Simple implementation of the {@link BookRepository}, that does not rely on any kind of sophisticated data base
 * or similar to find the requested items but rather on a in memory structure that keeps them all.
 *
 * @author Christian Lutz
 * 2018
 */
public class InMemoryBookRepository implements BookRepository {
	
	private Books books;
	
	/**
	 * Creates a new {@link InMemoryBookRepository}, that loads the books from the given {@link InputStream}.
	 *
	 * @param in that provides us with the necessary data.
	 * @throws InMemoryBookRepositoryInitializationException if there is something wrong with the given inputStream and
	 * we therefore cannot access the books with which this repository should be backed up.
	 */
	public InMemoryBookRepository(InputStream in) throws InMemoryBookRepositoryInitializationException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			this.books = mapper.readValue(in, Books.class);
		} catch (IOException ex) {
			throw new InMemoryBookRepositoryInitializationException(ex);
		}
	}

	@Override
	public Item getItemByIsbn(String isbn) {
		for (Item item : books.getItems()) {
			VolumeInfo volumeInfo = item.getVolumeInfo();
			List<IndustryIdentifier> industryIdentifiers = volumeInfo.getIndustryIdentifiers();
			for (IndustryIdentifier identifier : industryIdentifiers) {
				if (identifier.getIdentifier().equals(isbn)) {
					return item;
				}
			}
		}
		
		return null;
	}
	
	@Override
	public Item getItemById(String id) {
		for (Item item : books.getItems()) {
			if (item.getId().equals(id)) {
				return item;
			}
		}
		
		return null;
	}

	@Override
	public List<Item> getItemsByCategory(String categoryName) {
		List<Item> returnValues = new ArrayList<>();
		for (Item item : books.getItems()) {
			VolumeInfo volumeInfo = item.getVolumeInfo();
			List<String> categories = volumeInfo.getCategories();
			if (categories != null && categories.contains(categoryName)) {
				returnValues.add(item);
			}
		}
		return returnValues;
	}

	@Override
	public List<AuthorRating> getRatings() {
		Map<String, RatingCalculator> calculationMap = new HashMap<>();
		for (Item item : books.getItems()) {
			Double averageRating = item.getVolumeInfo().getAverageRating();
			if (averageRating == null) {
				continue;
			}
			List<String> authors = item.getVolumeInfo().getAuthors();
			for (String author : authors) {
				RatingCalculator ratingCalculator = calculationMap.get(author);
				if (ratingCalculator == null) {
					ratingCalculator = new RatingCalculator(author);
					calculationMap.put(author, ratingCalculator);
				}
				ratingCalculator.addRating(averageRating);
			}
		}
		
		List<AuthorRating> ratings = new ArrayList<>();
		calculationMap.values().forEach(c -> ratings.add(c.createAuthorRating()));
		Collections.sort(ratings, (a, b) -> {
			if (a.getAverageRating() > b.getAverageRating()) {
				return -1;
			} else if (b.getAverageRating() > a.getAverageRating()) {
				return 1;
			}
			return 0;
		});
		
		return ratings;
	}
	
	@Override
	public Collection<String> getCategories() {
		Set<String> categories = new HashSet<>();
		for (Item item : books.getItems()) {
			VolumeInfo volumeInfo = item.getVolumeInfo();
			if (volumeInfo == null) {
				continue;
			}
			List<String> cat = volumeInfo.getCategories();
			if (cat == null) {
				continue;
			}
			for (String category : cat) {
				if (category != null) {
					categories.add(category);
				}
			}
		}
		
		
		return new ArrayList<>(categories);
	}
	
	private class RatingCalculator {
		
		private final String authorName;
		private int ratingCount;
		private double sum;

		public RatingCalculator(String authorName) {
			super();
			this.authorName = authorName;
		}
		
		public AuthorRating createAuthorRating() {
			return new AuthorRating(this.authorName, this.sum / this.ratingCount);
		}

		public void addRating(double sum) {
			this.sum += sum;
			this.ratingCount++;
		}
	}
}
