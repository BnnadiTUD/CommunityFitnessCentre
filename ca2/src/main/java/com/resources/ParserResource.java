package resource;

import auth.AuthService;
import entities.User;
import service.ParserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/api/admin")
@Produces(MediaType.APPLICATION_JSON)
public class ParserResource {

    @Inject
    ParserService parserService;

    @Inject
    AuthService authService;

    private User requireAdmin(String token) {
        User u = authService.getUserFromToken(token);
        if (u == null || !u.active) throw new NotAuthorizedException("Invalid token");
        if (!authService.isAdmin(u)) throw new ForbiddenException("Admin required");
        return u;
    }

    @POST
    @Path("/parse")
    public String parse(@HeaderParam("Authorization") String token) throws Exception {
        requireAdmin(token);
        int count = parserService.runAllParsers();
        return "{\"imported\": " + count + "}";
    }
}

