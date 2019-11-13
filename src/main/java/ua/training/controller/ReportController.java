package ua.training.controller;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.jasperreports.engine.JRException;
import ua.training.pdfreporting.PdfReport;
import ua.training.services.UserService;
import ua.training.services.impl.PdfReportGenServiceImpl;

@Controller
@RequestMapping("/")
public class ReportController {

    private final UserService userService;
    private final PdfReportGenServiceImpl pdfReportGenService;

    @Autowired
    public ReportController(final UserService userService, final PdfReportGenServiceImpl pdfReportGenService){
        this.userService = userService;
        this.pdfReportGenService = pdfReportGenService;
    }

    @GetMapping
    public String getHome(){
        return "redirect:/employeeReport.pdf";
    }

    @GetMapping(value = "/employeeReport.pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseBody
    public HttpEntity<byte[]> getEmployeeReportPdf(final HttpServletResponse response) throws JRException, IOException, ClassNotFoundException {
        final PdfReport report = new PdfReport(userService.findAll().stream().map(Objects::toString).collect(Collectors.toList()));
        final byte[] data = pdfReportGenService.getReportPdf(report.getReport());

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_PDF);
        header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=employeeReport.pdf");
        header.setContentLength(data.length);

        return new HttpEntity<byte[]>(data, header);
    }


    @GetMapping(value = "/employeeReport.xlsx", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    @ResponseBody
    public HttpEntity<byte[]> getEmployeeReportXlsx(final HttpServletResponse response) throws JRException, IOException, ClassNotFoundException {
        final EmployeeReport report = new EmployeeReport(userService.findAll());
        final byte[] data = pdfReportGenService.getReportXlsx(report.getReport());

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=employeeReport.xlsx");
        header.setContentLength(data.length);

        return new HttpEntity<byte[]>(data, header);
    }
}