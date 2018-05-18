package com.booklibrary;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Starter {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LibraryConfiguration.class);
		
		context.start();
		Runtime.getRuntime().addShutdownHook(new Thread(() -> context.close()));
	}
}
