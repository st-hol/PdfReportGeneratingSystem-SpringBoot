//package ua.training.services.impl;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//import ua.training.services.UserService;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ReportServiceImplTest {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private ReportService reportService;
//
//    @MockBean
//    private ReportRepository reportRepository;
//
//
//    @Test
//    public void addReport() {
//        Report report = new Report();
//        report.setPerson(userService.findById(1L));
//        reportService.save(report);
//        Mockito.verify(reportRepository, Mockito.times(1)).save(report);
//    }
//
//}