package com.booklibrary.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.booklibrary.entities.IndustryIdentifier;
import com.booklibrary.entities.Item;
import com.booklibrary.entities.VolumeInfo;

public class ItemToReturnEntityTranslator {
	
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	private Item item;
	private VolumeInfo volumeInfo;
	
	public ItemToReturnEntityTranslator(Item item) {
		super();
		this.item = item;
	}

	public ReturnEntity translate() {
		ReturnEntity entity = new ReturnEntity();
		entity.setIsbn(getIsbn());
		entity.setTitle(getTitle());
		entity.setSubtitle(getSubtitle());
		entity.setPublisher(getPublisher());
		entity.setPublishedDate(getPublishedDate());
		entity.setDescription(getDescription());
		entity.setPageCount(getPageCount());
		entity.setThumbnailUrl(getThumbnailUrl());
		entity.setLanguage(getLanguage());
		entity.setPreviewLink(getPreviewLink());
		entity.setAverageRating(getAverageRating());
		entity.setAuthors(getAuthors());
		entity.setCategories(getCategories());
		
		return entity;
	}
	
	private String getIsbn() {
		List<IndustryIdentifier> identifiers = getVolumeInfo().getIndustryIdentifiers();
		for (IndustryIdentifier industryIdentifier : identifiers) {
			if (industryIdentifier.getType().equals("ISBN_13")) {
				return industryIdentifier.getIdentifier();
			}
		}
		
		throw new IllegalStateException();
	}
	
	private String getTitle() {
		return getVolumeInfo().getTitle();
	}
	
	private String getSubtitle() {
		return getVolumeInfo().getSubtitle();
	}
	
	private String getPublisher() {
		return getVolumeInfo().getPublisher();
	}
	
	private Long getPublishedDate() {
		String publishedDate = getVolumeInfo().getPublishedDate();
		
		Date date;
		try {
			date = new SimpleDateFormat(DATE_FORMAT).parse(publishedDate);
			return date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private String getDescription() {
		return getVolumeInfo().getDescription();
	}
	
	private Integer getPageCount() {
		return getVolumeInfo().getPageCount();
	}
	
	private String getThumbnailUrl() {
		return getVolumeInfo().getImageLinks().getThumbnail();
	}
	
	private String getLanguage() {
		return getVolumeInfo().getLanguage();
	}
	
	private String getPreviewLink() {
		return getVolumeInfo().getPreviewLink();
	}
	
	private Double getAverageRating() {
		return getVolumeInfo().getAverageRating();
	}
	
	private List<String> getAuthors() {
		return getVolumeInfo().getAuthors();
	}
	
	private List<String> getCategories() {
		return getVolumeInfo().getCategories();
	}
	
	private VolumeInfo getVolumeInfo() {
		if (this.volumeInfo == null) {
			this.volumeInfo = this.item.getVolumeInfo();
			
		}

		return this.volumeInfo;
	}

}
