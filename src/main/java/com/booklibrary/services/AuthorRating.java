package com.booklibrary.services;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "author", "averageRating" })
public class AuthorRating {

	@JsonProperty("author")
	private final String author;
	@JsonProperty("averageRating")
	private final Double averageRating;
	
	public AuthorRating(String author, Double averageRating) {
		super();
		this.author = author;
		this.averageRating = averageRating;
	}

	@JsonProperty("author")
	public String getAuthor() {
		return author;
	}

	@JsonProperty("averageRating")
	public Double getAverageRating() {
		return averageRating;
	}
}