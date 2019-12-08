package ua.training.controller;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.training.entities.ReportTemplate;
import ua.training.services.UserService;
import ua.training.services.impl.*;
import ua.training.util.Mail;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "/reporting")
public class ReportController {

    @Autowired
    private UserService userService;
    @Autowired
    private ReportTemplateServiceImpl reportTemplateService;
    @Autowired
    private TemplateFieldServiceImpl templateFieldService;
    @Autowired
    private PdfReportGenServiceImpl pdfReportGenService;
    @Autowired
    private ReportServiceImpl reportService;
    @Autowired
    private MailService mailService;


    @GetMapping("/choose-report")
    public String chooseReportTemplateForm(Model uiModel) {
        List<ReportTemplate> reportTemplates = reportTemplateService.findAll();
        uiModel.addAttribute("reportTemplates", reportTemplates);
        return "client/choose-report";
    }

    @PostMapping("/choose-report")
    public String chooseReportTemplate(@RequestParam Long idTemplate,
                                       Model uiModel) {
        ReportTemplate reportTemplate = reportTemplateService.findById(idTemplate);
        uiModel.addAttribute("reportTemplate", reportTemplate);
        return "client/fill-report";
    }



    //todo pagination show all with buttons save and email


    //report-downloading command
    @GetMapping(value = "/report.pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseBody
    public HttpEntity<byte[]> getEmployeeReportPdf(final HttpServletResponse response) throws JRException {
//        final PdfReport report = new PdfReport(reportService.findAll());
//        final byte[] data = pdfReportGenService.getReportPdf(report.getReport());
//
//        HttpHeaders header = new HttpHeaders();
//        header.setContentType(MediaType.APPLICATION_PDF);
//        header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=report.pdf");
//        header.setContentLength(data.length);
//
//        return new HttpEntity<>(data, header);
        return null;
    }

    //report-onEmail  command
    @GetMapping(value = "/get-by-email")
    @ResponseBody
    public String email() throws MessagingException {
        Mail mail = new Mail();
        mail.setFrom("sdaf12fds21@gmail.com");
        mail.setTo("stanislav.holovachuk@gmail.com");
        mail.setSubject("Sending Email Attachment Configuration Example");
        mail.setContent("This tutorial demonstrates how to send an email with attachment using Spring Framework.");

        mailService.sendSimpleMessage(mail);

        return "zbs";
    }

    //todo check Jasper method for placeholders


}


//    @GetMapping("/re")
//    public String getHome(){
//        return "redirect:/report.pdf";
//    }
//
//    @GetMapping(value = "/report.pdf", produces = MediaType.APPLICATION_PDF_VALUE)
//    @ResponseBody
//    public HttpEntity<byte[]> getEmployeeReportPdf(final HttpServletResponse response) throws JRException, ClassNotFoundException {
//        final PdfReport report = new PdfReport(reportService.findAll());
//        final byte[] data = pdfReportGenService.getReportPdf(report.getReport());
//
//        HttpHeaders header = new HttpHeaders();
//        header.setContentType(MediaType.APPLICATION_PDF);
//        header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=report.pdf");
//        header.setContentLength(data.length);
//
//        return new HttpEntity<byte[]>(data, header);
//    }