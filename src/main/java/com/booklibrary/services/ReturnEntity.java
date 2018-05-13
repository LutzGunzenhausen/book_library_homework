package com.booklibrary.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "isbn", "title", "subtitle", "publisher", "publishedDate", "description", "pageCount",
		"thumbnailUrl", "language", "previewLink", "averageRating", "authors", "categories" })
public class ReturnEntity {

	@JsonProperty("isbn")
	private String isbn;
	@JsonProperty("title")
	private String title;
	@JsonProperty("subtitle")
	private String subtitle;
	@JsonProperty("publisher")
	private String publisher;
	@JsonProperty("publishedDate")
	private Long publishedDate;
	@JsonProperty("description")
	private String description;
	@JsonProperty("pageCount")
	private Integer pageCount;
	@JsonProperty("thumbnailUrl")
	private String thumbnailUrl;
	@JsonProperty("language")
	private String language;
	@JsonProperty("previewLink")
	private String previewLink;
	@JsonProperty("averageRating")
	private Double averageRating;
	@JsonProperty("authors")
	private List<String> authors = null;
	@JsonProperty("categories")
	private List<String> categories = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("isbn")
	public String getIsbn() {
		return isbn;
	}

	@JsonProperty("isbn")
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	@JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty("subtitle")
	public String getSubtitle() {
		return subtitle;
	}

	@JsonProperty("subtitle")
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	@JsonProperty("publisher")
	public String getPublisher() {
		return publisher;
	}

	@JsonProperty("publisher")
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@JsonProperty("publishedDate")
	public Long getPublishedDate() {
		return publishedDate;
	}

	@JsonProperty("publishedDate")
	public void setPublishedDate(Long publishedDate) {
		this.publishedDate = publishedDate;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("pageCount")
	public Integer getPageCount() {
		return pageCount;
	}

	@JsonProperty("pageCount")
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	@JsonProperty("thumbnailUrl")
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	@JsonProperty("thumbnailUrl")
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	@JsonProperty("language")
	public String getLanguage() {
		return language;
	}

	@JsonProperty("language")
	public void setLanguage(String language) {
		this.language = language;
	}

	@JsonProperty("previewLink")
	public String getPreviewLink() {
		return previewLink;
	}

	@JsonProperty("previewLink")
	public void setPreviewLink(String previewLink) {
		this.previewLink = previewLink;
	}

	@JsonProperty("averageRating")
	public Double getAverageRating() {
		return averageRating;
	}

	@JsonProperty("averageRating")
	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}

	@JsonProperty("authors")
	public List<String> getAuthors() {
		return authors;
	}

	@JsonProperty("authors")
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	@JsonProperty("categories")
	public List<String> getCategories() {
		return categories;
	}

	@JsonProperty("categories")
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}