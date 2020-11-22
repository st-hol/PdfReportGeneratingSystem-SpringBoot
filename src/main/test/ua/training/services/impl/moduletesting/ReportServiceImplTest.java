package ua.training.services.impl.moduletesting;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ua.training.entities.Report;
import ua.training.exception.MyFileNotFoundException;
import ua.training.repositories.ReportRepository;
import ua.training.services.UserService;
import ua.training.services.impl.ReportServiceImpl;

/**
 * TASK 6: MODULE TESTING
 *
 * @author Stanislav_Holovachuk, Olena_Patsevko
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportServiceImplTest {

    @Mock
    private UserService userService;
    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private ReportServiceImpl reportService;

    @Before
    public void setUp(){

    }

    @Test
    public void addReport() {
        Report report = new Report();
        reportService.save(report);
        Mockito.verify(reportRepository, Mockito.times(1)).save(report);
    }

    @Test(expected = MyFileNotFoundException.class)
    public void getFile_notFound() {
        when(reportRepository.findById(anyLong())).thenReturn(Optional.empty());
        reportService.getFile(1L);
    }

    @Test
    public void getFile_proceedNormally() {
        when(reportRepository.findById(anyLong())).thenReturn(Optional.of(new Report()));
        Report report = reportService.getFile(1L);
        assertThat(report, is(notNullValue()));
    }

}