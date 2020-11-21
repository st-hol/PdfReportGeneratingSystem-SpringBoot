package ua.training.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ua.training.entities.Report;
import ua.training.repositories.ReportRepository;
import ua.training.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportServiceImplTest {

    @Mock
    private UserService userService;
    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private ReportServiceImpl reportService;

    @Test
    public void addReport() {
        Report report = new Report();
        report.setPerson(userService.findById(1L));
        reportService.save(report);
        Mockito.verify(reportRepository, Mockito.times(1)).save(report);
    }

}