package ua.training.services.impl;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.itextpdf.text.DocumentException;

public class PdfReportGenServiceImplTest {

    @Autowired
    @Qualifier
    private PdfReportGenServiceImpl pdfReportGenServiceImpl;

    @Test
    public void testSubstitute() throws IOException, DocumentException {
        String[] fields = new String[]{"1"};
        pdfReportGenServiceImpl.substituteFields("1.pdf", "1_.pdf", fields);
    }

}