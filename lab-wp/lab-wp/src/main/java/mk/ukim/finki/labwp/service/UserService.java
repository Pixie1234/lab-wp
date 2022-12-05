package mk.ukim.finki.labwp.service;

import mk.ukim.finki.labwp.model.User;

public interface UserService {
    User register(String username,String password,String repeatPassword);
}
