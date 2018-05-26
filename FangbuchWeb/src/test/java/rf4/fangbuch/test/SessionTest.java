package rf4.fangbuch.test;

import java.net.URI;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.internal.HttpUrlConnector;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.*;

import com.google.gson.Gson;

import rf4.fangbuch.server.FangbuchWeb;

public class SessionTest {
	@Test
	public void testRESTfulWebService() {
		String baseUrl = "http://localhost:4434";
		String name = "MeinName";

		// Testserver:
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(baseUrl),
				new ResourceConfig(FangbuchWeb.class));

		try {
			// Testclient:
			Client c = ClientBuilder.newClient();
			WebTarget target = c.target(baseUrl);

			// Pruefungen:
			Response txt = target.path(FangbuchWeb.webContextPath + "/Session/getOffeneSessions")
					.queryParam("benutzer", name).request(MediaType.APPLICATION_JSON).get(Response.class);

			Assert.assertNotNull(txt);

			String readEntity = txt.readEntity(String.class);

			Gson g = new Gson();
			String fromJson = g.fromJson(readEntity, String.class);
			Assert.assertEquals("Session", fromJson);

		} finally {
			// Testserver beenden:
			server.shutdown();
		}
	}
}