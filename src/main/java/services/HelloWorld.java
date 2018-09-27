package services;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("/helloworld")
@Stateless
public class HelloWorld {

	@GET
    @Produces("text/plain")
    public String getHtml() {
        return "H2G2";
    }
}