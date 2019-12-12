package ua.training.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.training.entities.ReportTemplate;
import ua.training.entities.TemplateField;

import java.util.List;


@Repository
public interface TemplateFieldRepository extends CrudRepository<TemplateField, Long> {


    @Query(nativeQuery = true,
            value = "select field_name from report_templates join template_fields " +
                    "using(id_template) where id_template = :id")
    List<String> findFieldNamesByReportType(@Param("id") long id);

    List<TemplateField> findAllByTemplate(ReportTemplate template);

}
