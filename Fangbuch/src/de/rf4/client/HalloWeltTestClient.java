package de.rf4.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class HalloWeltTestClient
{
    public static void main(String[] args)
    {
        String name = (args.length > 0) ? args[0] : "ich";
        String baseUrl = (args.length > 1) ? args[1] : "http://localhost:8080";
        String webContextPath = "/FangbuchServer";
        System.out.println("\nAngefragte URL: " + baseUrl + webContextPath + "?name=" + name);

        Client c = ClientBuilder.newClient();
        WebTarget target = c.target(baseUrl);

        System.out.println("\nTextausgabe:");
        System.out.println(target.path(webContextPath).queryParam("name", name).request(MediaType.TEXT_PLAIN).get(String.class));
        System.out.println("\nHTML-Ausgabe:");
        System.out.println(target.path(webContextPath).queryParam("name", name).request(MediaType.TEXT_HTML).get(String.class));
    }
}
