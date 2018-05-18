package com.booklibrary;

import java.net.URI;
import java.util.logging.Logger;

import javax.annotation.PreDestroy;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.ApplicationContext;

import com.booklibrary.services.Library;

//@Component
public class RestInterface {

	private static final String BASE_URI = "http://localhost:7777/";
	private static final String BASE_PACKAGE = "com.booklibrary.services";
	private HttpServer server;
	
	public RestInterface(Library library) {
		final ResourceConfig rc = new ResourceConfig().register(library);
//				packages(BASE_PACKAGE);
		server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
		System.out.println("REST-Server is running at " + BASE_URI);
	}
	
	/**
	 * Hook method that will be called when the {@link ApplicationContext} of spring will be closed. We will then also
	 * shut down our server.
	 */
	@PreDestroy
	public void stop() {
		Logger.getLogger(getClass().getName()).info("Shutting down the REST-Server.");
		server.shutdownNow();
	}
}
