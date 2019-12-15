package ua.training.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.training.entities.ReportTemplate;
import ua.training.entities.TemplateField;
import ua.training.payload.UploadFileResponse;
import ua.training.services.impl.PdfReportGenServiceImpl;
import ua.training.services.impl.ReportServiceImpl;
import ua.training.services.impl.ReportTemplateServiceImpl;
import ua.training.services.impl.TemplateFieldServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@RequestMapping(value = "/inspector")
public class AdminController {

    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private ReportTemplateServiceImpl reportTemplateService;
    @Autowired
    private TemplateFieldServiceImpl templateFieldService;
    @Autowired
    private PdfReportGenServiceImpl pdfReportGenService;
    @Autowired
    private ReportServiceImpl reportService;

    @GetMapping("/upload-template")
    public String uploadForm() {
        return "inspector/upload-template";
    }

    //stub
    @GetMapping(value = "/personal-cabinet")
    public String personalCabinet() {
        return "redirect:/inspector/upload-template";
    }

    @ResponseBody
    @PostMapping("/upload-template")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        ReportTemplate reportTemplate = reportTemplateService.storeFile(file);

        List<String> stampFields = pdfReportGenService.getFieldNamesOrdered(reportTemplate.getReportPdf());
        Set<TemplateField> templateFields = stampFields.stream()
                .map(str -> new TemplateField(str, reportTemplate))
                .map(templateFieldService::save)
                .collect(Collectors.toSet());
        reportTemplate.setFields(templateFields);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/inspector/download-file/")
                .path(String.valueOf(reportTemplate.getId()))
                .toUriString();

        return new UploadFileResponse(reportTemplate.getTemplateName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    //report-downloading command
    @GetMapping(value = "/download-file/{fileId}", produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseBody
    public HttpEntity<byte[]> getEmployeeReportPdf(@PathVariable String fileId) {
        ReportTemplate dbFile = reportTemplateService.getFile(Long.parseLong(fileId));
        final byte[] data = dbFile.getReportPdf();

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_PDF);
        header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=template" + fileId + ".pdf");
        header.setContentLength(data.length);

        return new HttpEntity<>(data, header);
    }


}


//        reportTemplateService.save(reportTemplate); //upd. with fields

//        Set<String> stampFields = pdfReportGenService.getFieldNames(reportTemplate.getReportPdf());
//        stampFields.stream()
//                .map(str -> new TemplateField(str, reportTemplate))
//                .forEach(templateFieldService::save);
//
//        Set<TemplateField> templateFields = new HashSet<>(
//                templateFieldService.findFieldsByReportType(reportTemplate));
//
//        reportTemplate.setFields(templateFields);
//        reportTemplateService.save(reportTemplate); //upd. with fields




//    @Autowired
//    private ReportService reportService;

//    @Autowired
//    private UserService userService;
//
//    @GetMapping(value = "/personal-cabinet")
//    public String personalCabinet() {
//        return "inspector/inspector-base";
//    }

//    @GetMapping(value = "/show-reports")
//    public String list(Model uiModel, Pageable pageable) {
//        User currentUser = userService.obtainCurrentPrincipleUser();
//        Page<Report> page = reportService.findAllReportsOfPersonsByAssignedInspector(currentUser, pageable);
//
//        uiModel.addAttribute("page", page);
//        uiModel.addAttribute("url", "/inspector/show-reports");
//
//        return "inspector/show-reports";
//    }


