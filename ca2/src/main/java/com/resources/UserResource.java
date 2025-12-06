package resource;

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

    @GET
    public List<User> getAll() {
        return uR.listAll();
    }

    @GET
    @Path("/{id}")
    public User getOne(@PathParam("id") Long id) {
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
    public User update(@PathParam("id") Long id, UpdateUserRequest req) {
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
    public void delete(@PathParam("id") Long id) {
        boolean deleted = uR.deleteById(id);
    }
}


