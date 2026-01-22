package resource;

import auth.AuthService;
import entities.User;
import repos.UserRepo;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/api/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserRepo uR;

    @Inject
    AuthService authService;

    private User requireAdmin(String token) {
        User u = authService.getUserFromToken(token);
        return u;
    }

    @GET
    public List<User> getAll(@HeaderParam("Authorization") String token) {
        requireAdmin(token);
        return uR.listAll();
    }

    @GET
    @Path("/{id}")
    public User getOne(@HeaderParam("Authorization") String token,
                       @PathParam("id") Long id) {
        requireAdmin(token);
        User u = uR.findById(id);
        return u;
    }

    public static class UpdateUserRequest {
        public String email;
        public String password;
        public Boolean admin;
        public Boolean active;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public User update(@HeaderParam("Authorization") String token,
                       @PathParam("id") Long id,
                       UpdateUserRequest req) {
        requireAdmin(token);
        User u = uR.findById(id);

        if (req.email != null) u.email = req.email;
        if (req.password != null) u.password = req.password;
        if (req.admin != null) u.admin = req.admin;
        if (req.active != null) u.active = req.active;

        return u;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@HeaderParam("Authorization") String token,
                       @PathParam("id") Long id) {
        requireAdmin(token);
        boolean deleted = uR.deleteById(id);
    }
}

