package ua.training.repositories;

import ua.training.entities.TaxableItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxableItemRepository extends CrudRepository<TaxableItem, Long> {
}


