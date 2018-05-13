package com.booklibrary;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Starter {
	
	private static final String BASE_URI = "http://localhost:8080/";
	private static final String BASE_PACKAGE = "com.booklibrary.services";

	public static void main(String[] args) throws IOException {
		final ResourceConfig rc = new ResourceConfig().packages(BASE_PACKAGE);
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
		System.out.println("REST-Server is running at " + BASE_URI);
		System.in.read();
		server.shutdownNow();
	}

}
