package ua.training.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import ua.training.entities.Report;
import ua.training.entities.User;

import java.util.List;

public interface ReportService {
    List<Report> findAll();

    List<Report> findAllByPerson(User user);

    Page<Report> findAllByPerson(User user, Pageable pageable);

    Report findById(Long id);

    Report save(Report report);

    void edit(Long id, Report changed);

    void check(Long id, Report changed);

    List<Report> findAllReportsOfPersonsByAssignedInspector(User user);

    Page<Report> findAllReportsOfPersonsByAssignedInspector(@Param("user") User user, Pageable pageable);

}