package com.booklibrary.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import com.booklibrary.entities.Item;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ItemToReturnEntityTranslatorTest {
	
	private Item item;
	
	@Before
	public void initialize() throws IOException {
		InputStream in = ItemToReturnEntityTranslatorTest.class.getResourceAsStream("ExampleItem.json");
		this.item = new ObjectMapper().readValue(in, Item.class);
	}
	
	@Test
	public void testTranslation() {
		ItemToReturnEntityTranslator translator = new ItemToReturnEntityTranslator(item);
		ReturnEntity returnEntity = translator.translate();
		assertEquals("9780080568782", returnEntity.getIsbn());
		assertEquals("TCP/IP Sockets in Java", returnEntity.getTitle());
		assertEquals("Practical Guide for Programmers", returnEntity.getSubtitle());
		assertEquals("Morgan Kaufmann", returnEntity.getPublisher());
		assertEquals((Long) 1314568800000L, returnEntity.getPublishedDate());
		assertEquals("The networking capabilities of the Java platform have been extended considerably "
				+ "since the first edition of the book.", returnEntity.getDescription());
		assertEquals((Integer) 192, returnEntity.getPageCount());
		assertEquals("http://books.google.com/books/content?id=lfHo7uMk7r4C&printsec=frontcover"
				+ "&img=1&zoom=1&edge=curl&source=gbs_api", returnEntity.getThumbnailUrl());
		assertEquals("en", returnEntity.getLanguage());
		assertEquals("http://books.google.pl/books?id=lfHo7uMk7r4C&printsec=frontcover&"
				+ "dq=java&hl=&cd=38&source=gbs_api", returnEntity.getPreviewLink());
		assertEquals(4.0, returnEntity.getAverageRating(), 0.0);
		
		assertThat(returnEntity.getAuthors()).isNotEmpty().containsExactly(
				"Kenneth L. Calvert", "Michael J. Donahoo");
		assertThat(returnEntity.getCategories()).isNotEmpty().containsExactly("Computers");
		
		assertEquals("en", returnEntity.getLanguage());
	}
}
