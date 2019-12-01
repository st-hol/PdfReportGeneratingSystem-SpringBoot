package ua.training.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.training.entities.ReportTemplate;

@Repository
public interface ReportTemplateRepository extends CrudRepository<ReportTemplate, Long> {

}
