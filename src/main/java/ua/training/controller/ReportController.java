package ua.training.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.jasperreports.engine.JRException;
import ua.training.entities.Mail;
import ua.training.pdfreporting.PdfReport;
import ua.training.services.ReportService;
import ua.training.services.UserService;
import ua.training.services.impl.MailService;
import ua.training.services.impl.PdfReportGenServiceImpl;

@Controller
public class ReportController {

    private final UserService userService;
    private final ReportService reportService;
    private final MailService mailService;
    private final PdfReportGenServiceImpl pdfReportGenService;

    @Autowired
    public ReportController(final UserService userService,
                            final ReportService reportService,
                            final MailService mailService,
                            final PdfReportGenServiceImpl pdfReportGenService){
        this.userService = userService;
        this.reportService = reportService;
        this.mailService = mailService;
        this.pdfReportGenService = pdfReportGenService;
    }

    @GetMapping("/re")
    public String getHome(){
        return "redirect:/report.pdf";
    }

    @GetMapping(value = "/report.pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseBody
    public HttpEntity<byte[]> getEmployeeReportPdf(final HttpServletResponse response) throws JRException, ClassNotFoundException {
        final PdfReport report = new PdfReport(reportService.findAll());
        final byte[] data = pdfReportGenService.getReportPdf(report.getReport());

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_PDF);
        header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=report.pdf");
        header.setContentLength(data.length);

        return new HttpEntity<byte[]>(data, header);
    }

    @GetMapping(value = "/mail")
    @ResponseBody
    public String email() throws MessagingException {
        Mail mail = new Mail();
        mail.setFrom("sdaf12fds21@gmail.com");
        mail.setTo("info@memorynotfound.com");
        mail.setSubject("Sending Email Attachment Configuration Example");
        mail.setContent("This tutorial demonstrates how to send an email with attachment using Spring Framework.");

        mailService.sendSimpleMessage(mail);

        return "zbs";
    }

//    @GetMapping(value = "/employeeReport.xlsx", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
//    @ResponseBody
//    public HttpEntity<byte[]> getEmployeeReportXlsx(final HttpServletResponse response) throws JRException, IOException, ClassNotFoundException {
//        final EmployeeReport report = new EmployeeReport(userService.findAll());
//        final byte[] data = pdfReportGenService.getReportXlsx(report.getReport());
//
//        HttpHeaders header = new HttpHeaders();
//        header.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
//        header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=employeeReport.xlsx");
//        header.setContentLength(data.length);
//
//        return new HttpEntity<byte[]>(data, header);
//    }
}