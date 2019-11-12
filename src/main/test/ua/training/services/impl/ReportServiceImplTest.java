package ua.training.services.impl;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import ua.training.entities.Report;
import ua.training.entities.User;
import ua.training.repositories.ReportRepository;
import ua.training.repositories.UserRepository;
import ua.training.services.ReportService;
import ua.training.services.UserService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ReportService reportService;

    @MockBean
    private ReportRepository reportRepository;


    @Test
    public void addReport() {
        Report report = new Report();
        report.setPerson(userService.findById(1L));
        reportService.save(report);
        Mockito.verify(reportRepository, Mockito.times(1)).save(report);
    }

}