package mk.ukim.finki.labwp.service;

import mk.ukim.finki.labwp.model.User;

public interface AuthService {
    User login(String username, String password);
}
