package ua.training.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.training.entities.ReportTemplate;
import ua.training.entities.TemplateField;
import ua.training.repositories.TemplateFieldRepository;

import java.util.List;


@Service
public class TemplateFieldServiceImpl {

    @Autowired
    private TemplateFieldRepository templateFieldRepository;

    public List<String> findFieldNamesByReportType(long id) {
        return templateFieldRepository.findFieldNamesByReportType(id);
    }

    public List<TemplateField> findFieldsByReportType(ReportTemplate template) {
        return templateFieldRepository.findAllByTemplate(template);
    }

    public TemplateField save(TemplateField templateField) {
        return templateFieldRepository.save(templateField);
    }
}