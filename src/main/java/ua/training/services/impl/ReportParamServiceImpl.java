package ua.training.services.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.training.entities.Report;
import ua.training.entities.ReportParam;
import ua.training.repositories.ReportParamRepository;

import java.util.List;

@Service
public class ReportParamServiceImpl {

    @Autowired
    private ReportParamRepository reportParamRepository;

    public ReportParam save(ReportParam reportParam) {
        return reportParamRepository.save(reportParam);
    }

    public List<ReportParam> findAll() {
        return Lists.newArrayList(reportParamRepository.findAll());
    }

    public List<ReportParam> findByReport(Report report) {
        return reportParamRepository.findParamsByReport(report);
    }

}
