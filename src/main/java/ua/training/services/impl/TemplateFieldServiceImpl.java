package ua.training.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.training.repositories.TemplateFieldRepository;

import java.util.List;


@Service
public class TemplateFieldServiceImpl {

    @Autowired
    private TemplateFieldRepository templateFieldRepository;

    public List<String> findFieldNamesByReportType(long id) {
        return templateFieldRepository.findFieldNamesByReportType(id);
    }
}