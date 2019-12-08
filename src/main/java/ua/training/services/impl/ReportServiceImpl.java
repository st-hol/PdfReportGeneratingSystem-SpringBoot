package ua.training.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.training.entities.Report;
import ua.training.entities.User;
import ua.training.exception.MyFileNotFoundException;
import ua.training.repositories.ReportRepository;

@Service
public class ReportServiceImpl {

    @Autowired
    ReportRepository reportRepository;


    public Report save(Report report) {
        return reportRepository.save(report);
    }


    public Page<Report> findAllByPerson(User user, Pageable pageable) {
        return reportRepository.findAllByPerson(user, pageable);
    }

    public Report getFile(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + id));
    }
}
