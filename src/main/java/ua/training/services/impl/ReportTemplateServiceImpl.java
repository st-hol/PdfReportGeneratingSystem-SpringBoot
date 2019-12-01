package ua.training.services.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.training.entities.ReportTemplate;
import ua.training.repositories.ReportTemplateRepository;

import java.util.List;


@Service
public class ReportTemplateServiceImpl {

    @Autowired
    private ReportTemplateRepository reportTemplateRepository;

    public List<ReportTemplate> findAll() {
        return Lists.newArrayList(reportTemplateRepository.findAll());
    }

    public ReportTemplate findById(Long id) {
        return reportTemplateRepository.findById(id).orElse(null);
    }

    public ReportTemplate save(ReportTemplate report) {
        return reportTemplateRepository.save(report);
    }

}