package rf4.fangbuch.server;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.process.internal.RequestScoped;

import com.google.gson.Gson;

@Path(FangbuchWeb.webContextPath)
@RequestScoped
public class FangbuchWeb
{
    public static final String webContextPath = "Fangbuch";
	private Logger log = Logger.getLogger(getClass().getName());
    
	@EJB
	private SessionManager sessionManager;
	
    @POST
    @Path("/Session/erstelleSession")
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
    @Path("/Session/getOffeneSessions")
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    public Response getOffeneSession(@QueryParam("benutzer") String benutzer)
    {
        try
        {
        	log.log(Level.INFO, "SessionManager: " + sessionManager);
            return builOKResponse(sessionManager.getOffeneSession(benutzer));
        }
        catch (Exception e)
        {
            log.log(Level.WARNING, "Fehler beim Ermittlen der offnen Sessions", e);
            return Response.serverError().build();
        }
    }

	private Response builOKResponse(Object obj) {
		Gson g = new Gson();
		String json = g.toJson(obj);
		
		
		return Response.ok(json).build();
	}
}
