package com.booklibrary.repository;

import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.booklibrary.entities.Item;
import com.booklibrary.services.AuthorRating;

/**
 * This is the basic interface for all kinds of Repositories, that provide us with Books according to some kind of request.
 *
 * @author Christian Lutz
 * 2018
 */
public interface BookRepository {

	/**
	 * Looks through the repository to find an item with the given ISBN. If none is found <code>null</code> is returned.
	 *
	 * @param isbn for which to look for an item.
	 * @return the item with the given ISBN or <code>null</code>, if there is no such item.
	 */
	@Nullable
	Item getItemByIsbn(@Nonnull String isbn);

	/**
	 * Looks through the repository to find an item with the given bookId. If none is found <code>null</code> is returned.
	 *
	 * @param id for which to look for an item.
	 * @return the item with the given ISBN or <code>null</code>, if there is no such item.
	 */
	@Nullable
	Item getItemById(@Nonnull String id);

	/**
	 * Searches for all the items in this repository, that have the given categoryName set.
	 *
	 * @param categoryName for which to look for items. Must not be <code>null</code>.
	 * @return the items that have the given categoryName set. Will never be <code>null</code>, but can be an empty list if
	 * there are no such books.
	 */
	@Nonnull
	List<Item> getItemsByCategory(@Nonnull String categoryName);

	/**
	 * Checks through all the books, collects it's authors and calculates their rating.
	 *
	 * @return the list of {@link AuthorRating}. This will never be <code>null</code>.
	 */
	@Nonnull
	List<AuthorRating> getRatings();
	
	@Nonnull
	Collection<String> getCategories();

}
