package ua.training.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class PdfReportGenServiceImplTest {

    @Autowired
    @Qualifier
    private PdfReportGenServiceImpl pdfReportGenServiceImpl;

//    @Test
//    public void testSubstitute() throws IOException, DocumentException {
//        String[] fields = new String[]{"name"};
//        String[] values = new String[]{"1"};
//        pdfReportGenServiceImpl.substituteFields("1.pdf", "1_.pdf", fields, values);
//    }

}

