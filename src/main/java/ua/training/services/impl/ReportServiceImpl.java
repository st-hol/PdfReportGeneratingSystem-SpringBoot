package ua.training.services.impl;

import com.google.common.collect.Lists;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.training.entities.Report;
import ua.training.entities.User;
import ua.training.repositories.ReportRepository;
import ua.training.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public List<Report> findAll() {
        return Lists.newArrayList(reportRepository.findAll());
    }

    @Override
    public List<Report> findAllByPerson(User user) {
        return Lists.newArrayList(reportRepository.findAllByPerson(user));
    }

    @Override
    public Report findById(Long id) {
        return reportRepository.findById(id).get();
    }

    @Override
    public Report save(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public void edit(Long id, Report changed) {
        Report updated = reportRepository.findById(id).get();
        updated.setCompanyName(changed.getCompanyName());
        updated.setTaxpayerCode(changed.getTaxpayerCode());
        updated.setShouldBeChanged(false);//reset flag
        reportRepository.save(updated);
    }

    @Override
    public void check(Long id, Report changed) {
        Report updated = reportRepository.findById(id).get();
        updated.setAccepted(changed.isAccepted());
        updated.setShouldBeChanged(changed.isShouldBeChanged());
        updated.setInspectorComment(changed.getInspectorComment());
        reportRepository.save(updated);
    }

    @Override
    public List<Report> findAllReportsOfPersonsByAssignedInspector(User user) {
        return Lists.newArrayList(reportRepository.findAllReportsOfPersonsByAssignedInspector(user));
    }


    @Override
    public Page<Report> findAllByPerson(User user, Pageable pageable) {
        return reportRepository.findAllByPerson(user, pageable);
    }

    @Override
    public Page<Report> findAllReportsOfPersonsByAssignedInspector(User user, Pageable pageable) {
        return reportRepository.findAllReportsOfPersonsByAssignedInspector(user, pageable);
    }

    @Autowired
    public void setReportRepository(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

}