package de.rf4.server;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path("Fangbuch/Session")
@Stateless
public class FangbuchWeb
{
    private Logger log = Logger.getLogger(getClass().getName());
    
    @POST
    @Path("erstelleSession")
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    public Response erstelleSession(@QueryParam("benutzer") String benutzer)
    {
        try
        {
            
        }
        catch (Exception e)
        {
            log.log(Level.WARNING, "Fehler beim Erstellen einer neuen Session", e);
            return Response.serverError().build();
        }
        
        return Response.ok().build();
    }

    @GET
    @Path("getOffeneSessions")
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    public Response getOffeneSessions(@QueryParam("benutzer") String benutzer)
    {
        try
        {
            Gson g = new Gson();
            String json = g.toJson("Session");
            return Response.ok(json).build();
        }
        catch (Exception e)
        {
            log.log(Level.WARNING, "Fehler beim Ermittlen der offnen Sessions", e);
            return Response.serverError().build();
        }
    }
}
