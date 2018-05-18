package com.booklibrary.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationRepositoryProvider implements RepositoryProvider {

	@Override
	public BookRepository getBookRepository() {
		try {
			InputStream in = getStreamFromConfig();
			if (in == null) {
				in = getClass().getClassLoader().getResourceAsStream(
						"com/booklibrary/services/books.json");
			}
			return new InMemoryBookRepository(in);
		} catch (IOException | InMemoryBookRepositoryInitializationException ex) {
			throw new IllegalStateException(ex);
		}
	}
	
	private InputStream getStreamFromConfig() throws IOException {
		File file = new File("Configuration.properties");
		if (!file.exists()) {
			return null;
		}
		Properties properties = new Properties();
		properties.load(new FileReader(file));
		String property = properties.getProperty("JsonPath");
		if (property == null || property.trim().isEmpty()) {
			return null;
		} else {
			return new FileInputStream(new File(property));
		}
	}

}
