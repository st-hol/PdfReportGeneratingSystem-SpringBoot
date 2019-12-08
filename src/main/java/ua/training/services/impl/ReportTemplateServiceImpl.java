package ua.training.services.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ua.training.entities.ReportTemplate;
import ua.training.exception.FileStorageException;
import ua.training.exception.MyFileNotFoundException;
import ua.training.repositories.ReportTemplateRepository;

import java.io.IOException;
import java.util.List;
import java.util.Objects;


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


    public ReportTemplate storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            ReportTemplate reportTemplate = new ReportTemplate();
            reportTemplate.setTemplateName("g");
            reportTemplate.setReportPdf(file.getBytes());

            return reportTemplateRepository.save(reportTemplate);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public ReportTemplate getFile(Long id) {
        return reportTemplateRepository.findById(id)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + id));
    }

}