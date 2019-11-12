package ua.training.services;


import ua.training.entities.Role;

import java.util.Set;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLoginAfterReg(String username, String password);

    Set<Role> getLoggedUserRoles();
}
