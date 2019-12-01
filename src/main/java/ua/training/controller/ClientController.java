package ua.training.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.training.entities.Report;
import ua.training.entities.User;
import ua.training.services.ReportService;
import ua.training.services.UserService;

@Controller
@RequestMapping(value = "/client")
public class ClientController {

    private static final Logger LOG = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ReportService reportService;


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


    @GetMapping("/report-done")
    public String successPageReportDone() {
        return "client/report-done";
    }

}
