package ua.training.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.training.entities.TemplateField;


@Repository
public interface TemplateFieldRepository extends CrudRepository<TemplateField, Long> {

}
