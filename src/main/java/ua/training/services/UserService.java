package ua.training.services;


import org.springframework.data.repository.query.Param;
import ua.training.entities.TaxableItem;
import ua.training.entities.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id);

    User save(User user);

    User findByUsername(String username);

    void registerUser(User user);

    User obtainCurrentPrincipleUser();

    Long calcSumOfPriceByTaxableItemsForUser(User user);

}