package ua.training.controller;

import com.itextpdf.text.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.training.entities.*;
import ua.training.payload.SendFileResponse;
import ua.training.services.UserService;
import ua.training.services.impl.*;
import ua.training.util.Mail;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping(value = "/client")
public class ClientController {

    private static final Logger LOG = LoggerFactory.getLogger(ClientController.class);

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
    private ReportParamServiceImpl reportParamService;
    @Autowired
    private MailService mailService;



    @GetMapping("/personal-cabinet")
    public String personalCabinet() {
        return "client/client-base";
    }

    @GetMapping("/show-reports")
    public String list(Model uiModel, Pageable pageable) {
        User currentUser = userService.obtainCurrentPrincipleUser();
        Page<Report> page = reportService.findAllByPerson(currentUser, pageable);

        uiModel.addAttribute("page", page);
        uiModel.addAttribute("url", "/client/show-reports");

        return "client/show-reports";
    }

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


    @PostMapping("/fill-report")
    public String fillReport(@RequestParam Map<String, String> allRequestParams, Model uiModel)
            throws IOException, DocumentException {

        ReportTemplate reportTemplate = reportTemplateService.findById(
                Long.parseLong(allRequestParams.get("templateId")));

        allRequestParams.remove("templateId"); //so in map only params left

        User loggedInUser = userService.obtainCurrentPrincipleUser();

        Report report = new Report();
        report.setReportType(reportTemplate);
        report.setPerson(loggedInUser);
        report.setCompletionTime(Calendar.getInstance().getTime());

        Set<ReportParam> reportParams = new HashSet<>();
        for (Map.Entry<String, String> param : allRequestParams.entrySet()) {
            ReportParam reportParam = new ReportParam();
            reportParam.setFieldName(param.getKey());
            reportParam.setFieldValue(param.getValue());
            reportParam.setReport(report);

            reportParams.add(reportParam);
        }

        String[] fieldNames = templateFieldService.findFieldsByReportType(report.getReportType())
                .stream()
                .map(TemplateField::getFieldName)
                .toArray(String[]::new);

        String[] fieldValues = reportParams.stream()
                .map(ReportParam::getFieldValue)
                .toArray(String[]::new);

        byte[] toDownload = pdfReportGenService.substituteFields(reportTemplate.getReportPdf(),
                fieldNames, fieldValues);

        report.setReportPdf(toDownload);
        report.setReportParams(reportParams);
        reportService.save(report);

        for (ReportParam reportParam : reportParams) {
            reportParamService.save(reportParam);
        }

        uiModel.addAttribute("reportId", report.getId());
        return "client/report-done";
//        return "redirect:/client/downloadFile/" + report.getId();
    }

    //report-downloading command
    @GetMapping(value = "/download-file/{fileId}", produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseBody
    public HttpEntity<byte[]> downloadReportPdf(@PathVariable String fileId) {
        Report dbFile = reportService.getFile(Long.parseLong(fileId));
        final byte[] data = dbFile.getReportPdf();

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_PDF);
        header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=report" + fileId + ".pdf");
        header.setContentLength(data.length);

        return new HttpEntity<>(data, header);
    }

    @GetMapping("/report-done")
    public String successPageReportDone() {
        return "client/report-done";
    }

    //report-onEmail  command
    @GetMapping(value = "/send-by-email/{fileId}")
    @ResponseBody
    public SendFileResponse email(@PathVariable String fileId) throws MessagingException {
        User currentUser = userService.obtainCurrentPrincipleUser();

        Mail mail = new Mail();
        mail.setFrom("AUTO-PDF SYSTEM");
        mail.setTo(currentUser.getUsername());
        mail.setSubject("Sending PDF-report as e-mail attachment");
        mail.setContent("Your PDF-report attached to this mail.");

        Report report = reportService.getFile(Long.parseLong(fileId));
        byte[] pdfBytes = report.getReportPdf();

        //check this report belongs to user
        if (!currentUser.getReports().contains(report)) {
            return new SendFileResponse(false, report.getReportType().getTemplateName(),
                    currentUser.getUsername());
        }

        mailService.sendMailWithAttachment(mail, pdfBytes);
        return new SendFileResponse(true, report.getReportType().getTemplateName(),
                currentUser.getUsername());
    }        //return "redirect:/client/personal-cabinet";


}




//    @GetMapping("/fill-report")
//    public String fillReportForm(@RequestParam Long idTemplate) throws IOException, DocumentException {
//        //todo get all fields
////        String[] fieldValues = new String[]{"value"};
////        String[] fieldNames = new String[]{"name"};
//        String[] fieldValues = {"val"};
//        ReportTemplate reportTemplate = reportTemplateService.findById(idTemplate);
//        String[] fieldNames = templateFieldService
//                .findFieldNamesByReportType(reportTemplate.getId()).toArray(new String[0]);
//
//        pdfReportGenService.substituteFields("1.pdf", "_1.pdf", fieldValues, fieldNames);
//
//        User loggedInUser = userService.obtainCurrentPrincipleUser();
//
//        Report report = new Report();
//        report.setReportType(reportTemplate);
//        report.setPerson(loggedInUser);
//        report.setFilledValues(String.join(" ", fieldValues));
//        reportService.save(report);
//
//        return "redirect:/client/report-done";
//        //todo redirect to show all
//    }