package com.booklibrary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.booklibrary.repository.ConfigurationRepositoryProvider;
import com.booklibrary.repository.InMemoryBookRepositoryInitializationException;
import com.booklibrary.repository.RepositoryProvider;
import com.booklibrary.services.Library;

@Configuration
public class LibraryConfiguration {
	
	@Bean
	public RepositoryProvider repositoryProvider() {
		return new ConfigurationRepositoryProvider();
	}
	
	@Bean
	public Library getLibrary() throws InMemoryBookRepositoryInitializationException {
		return new Library(repositoryProvider());
	}
	
	@Bean
	public RestInterface getRestInterface(Library library) {
		return new RestInterface(library);
	}

}
