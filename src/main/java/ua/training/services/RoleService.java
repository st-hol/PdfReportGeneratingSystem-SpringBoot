package ua.training.services;


import ua.training.entities.Role;
import ua.training.entities.User;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role findById(Long id);
    Role save(Role role);

    List<Role> findAllByUser(User user);
}