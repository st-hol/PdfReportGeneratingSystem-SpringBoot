package ua.training.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.training.entities.ReportTemplate;
import ua.training.payload.UploadFileResponse;
import ua.training.services.impl.ReportTemplateServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/upl")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private ReportTemplateServiceImpl reportTemplateService;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        ReportTemplate reportTemplate = reportTemplateService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/upl/downloadFile/")
                .path(String.valueOf(reportTemplate.getId()))
                .toUriString();

        return new UploadFileResponse(reportTemplate.getTemplateName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }
//
//    @GetMapping("/downloadFile/{fileId}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
//        // Load file from database
//        ReportTemplate dbFile = reportTemplateService.getFile(Long.parseLong(fileId));
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.APPLICATION_PDF)
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getTemplateName() + "\"")
//                .body(new ByteArrayResource(dbFile.getReportPdf()));
//    }

    //report-downloading command
    @GetMapping(value = "/downloadFile/{fileId}", produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseBody
    public HttpEntity<byte[]> getEmployeeReportPdf(@PathVariable String fileId) {
        ReportTemplate dbFile = reportTemplateService.getFile(Long.parseLong(fileId));
        final byte[] data = dbFile.getReportPdf();

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_PDF);
        header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=report.pdf");
        header.setContentLength(data.length);

        return new HttpEntity<>(data, header);
    }

}
