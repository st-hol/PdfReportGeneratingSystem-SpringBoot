package ua.training.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.training.entities.TaxableItem;
import ua.training.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    @Query("select sum(ti.price) as total from User u join u.taxableItems ti where u = :user")
    Long calcSumOfPriceByTaxableItemsForUser(@Param("user") User user);

}
