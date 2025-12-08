package resource;

import entities.Emissions;
import entities.User;
import auth.AuthService;
import service.EmissionService;
import parser.XmlEmissionParser;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/emissions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmissionResource {

    @Inject
    EmissionService emissionService;

    @Inject
    AuthService authService;

    @Inject
    XmlEmissionParser parser;

    private User requireUser(String token) {
        User u = authService.getUserFromToken(token);
        if (u == null)
            throw new NotAuthorizedException("You must be logged in");
        return u;
    }


    @POST
    @Path("/import/xml")
    public int importXml(@HeaderParam("Authorization") String token) throws Exception {
        requireUser(token); // must be logged in
        return parser.parseAndSave();
    }


    @GET
    public List<Emissions> getAll(@HeaderParam("Authorization") String token) {
        requireUser(token);
        return emissionService.getAll();
    }

    @GET
    @Path("/{id}")
    public Emissions getOne(@HeaderParam("Authorization") String token,
                            @PathParam("id") Long id) {
        requireUser(token);
        return emissionService.getById(id);
    }

    @GET
    @Path("/name/{name}")
    public List<Emissions> getByCategory(@HeaderParam("Authorization") String token,
                                         @PathParam("name") String name) {
        requireUser(token);
        return emissionService.getByCategory(code);
    }

    @PUT
    @Path("/{id}/approve")
    public Emissions approve(@HeaderParam("Authorization") String token,
                             @PathParam("id") Long id) {
        User approver = requireUser(token);
        return emissionService.approve(id, approver);
    }

    @POST
    public Response create(@HeaderParam("Authorization") String token, Emissions e) {
        requireUser(token);
        return Response.status(Response.Status.CREATED)
                .entity(emissionService.create(e))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Emissions update(@HeaderParam("Authorization") String token,
                            @PathParam("id") Long id,
                            Emissions e) {
        requireUser(token);
        return emissionService.update(id, e);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@HeaderParam("Authorization") String token,
                           @PathParam("id") Long id) {
        requireUser(token);
        emissionService.delete(id);
        return Response.noContent().build();
    }
}
