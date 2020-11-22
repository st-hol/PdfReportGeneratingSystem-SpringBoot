package ua.training.services.impl.moduletesting;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import ua.training.entities.ReportTemplate;
import ua.training.exception.MyFileNotFoundException;
import ua.training.repositories.ReportTemplateRepository;
import ua.training.services.impl.ReportTemplateServiceImpl;

/**
 * TASK 6: MODULE TESTING
 *
 * @author Stanislav_Holovachuk, Olena_Patsevko
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportTemplateServiceImplTest {

    @Mock
    private ReportTemplateRepository reportTemplateRepository;

    @InjectMocks
    private ReportTemplateServiceImpl reportTemplateService;

    @Captor
    private ArgumentCaptor<ReportTemplate> reportTemplateArgumentCaptor;

    @Before
    public void setUp() {
    }

    @Test
    public void storeFile() {
        //given
        MockMultipartFile file = new MockMultipartFile("name","name.pdf", "contentType", new byte[0]);

        //when
        reportTemplateService.storeFile(file);

        //then
        verify(reportTemplateRepository).save(reportTemplateArgumentCaptor.capture());
        assertThat(reportTemplateArgumentCaptor.getValue().getTemplateName(), is("name"));
    }

    @Test
    public void getFile() {
        //given
        final long id = 1L;
        when(reportTemplateRepository.findById(1L))
                .thenReturn(java.util.Optional.of(new ReportTemplate()));

        //when
        reportTemplateService.getFile(id);

        //then
        verify(reportTemplateRepository).findById(1L);
    }

    @Test(expected = MyFileNotFoundException.class)
    public void getFile_notFound() {
        //given
        final long id = 1L;
        when(reportTemplateRepository.findById(1L))
                .thenReturn(java.util.Optional.empty());

        //when
        reportTemplateService.getFile(id);

        //then
        verify(reportTemplateRepository).findById(1L);
    }
}
