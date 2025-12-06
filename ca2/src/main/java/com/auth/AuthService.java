package auth;

import entities.User;
import repos.UserRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AuthService {

    @Inject
    UserRepo userRepo;

    public String login(String email, String password) {
        User u = userRepo.findByEmail(email);
        if (u == null) return null;
        if (!u.active) return null;
        if (!u.password.equals(password)) return null;

        // Token is just the user's email
        return email;
    }

    public User getUserFromToken(String token) {
        if (token == null || token.isBlank()) return null;
        return userRepo.findByEmail(token);
    }

    public boolean isAdmin(User u) {
        return u != null && u.admin;
    }
}


