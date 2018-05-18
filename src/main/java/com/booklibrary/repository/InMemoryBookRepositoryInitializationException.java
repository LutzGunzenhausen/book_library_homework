package com.booklibrary.repository;

/**
 * Special exception to indicate, that something went wrong with creating the BookRepository.
 *
 * @author Christian Lutz
 * 2018
 */
public class InMemoryBookRepositoryInitializationException extends Exception {
	private static final long serialVersionUID = 1346558996463753542L;

	/**
	 * Creates a new {@link InMemoryBookRepositoryInitializationException} with its cause as a
	 * {@link Throwable}
	 *
	 * @param cause that made it impossible to create the {@link InMemoryBookRepository}.
	 */
	public InMemoryBookRepositoryInitializationException(Throwable cause) {
		super(cause);
	}
}
