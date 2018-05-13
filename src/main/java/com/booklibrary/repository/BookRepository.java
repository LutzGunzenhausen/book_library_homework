package com.booklibrary.repository;

import java.util.List;

import javax.annotation.Nullable;

import com.booklibrary.entities.Item;
import com.booklibrary.services.AuthorRating;

public interface BookRepository {

	@Nullable
	Item getItemByIsbn(String isbn);

	List<Item> getItemsByCategory(String categoryName);

	List<AuthorRating> getRatings();

}
