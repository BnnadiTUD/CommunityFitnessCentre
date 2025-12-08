package resource;

import auth.AuthService;
import entities.User;
import repos.UserRepo;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/api/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    UserRepo userRepo;

    @Inject
    AuthService authService;

    public static class RegisterRequest {
        public String email;
        public String password;
    }

    @POST
    @Path("/register")
    @Transactional
    public User register(RegisterRequest req) {
        if (req.email == null || req.password == null)
            throw new BadRequestException("Email and password required");

        if (userRepo.findByEmail(req.email) != null)
            throw new BadRequestException("Email already registered");

        User u = new User();
        u.email = req.email;
        u.password = req.password;
        u.admin = false;
        u.active = true;

        userRepo.persist(u);
        return u;
    }

    public static class LoginRequest {
        public String email;
        public String password;
    }

    public static class LoginResponse {
        public String token;
        public User user;
    }

    @POST
    @Path("/login")
    public LoginResponse login(LoginRequest req) {
        String token = authService.login(req.email, req.password);
        if (token == null)
            throw new NotAuthorizedException("Invalid credentials");

        LoginResponse resp = new LoginResponse();
        resp.token = token;
        resp.user = userRepo.findByEmail(req.email);
        return resp;
    }
}

