package services;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("hei")
@Stateless
public class HelloWorld {

	@GET
    //@Produces("text/plain")
    public Response getHtml() {

        return Response.ok("hæææææææææi").build();
    //return "H2G2";
    }
}