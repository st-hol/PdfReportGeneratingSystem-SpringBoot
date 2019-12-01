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
@RequestMapping(value = "/inspector")
public class AdminController {

    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private ReportService reportService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/personal-cabinet")
    public String personalCabinet() {
        return "inspector/inspector-base";
    }

    @GetMapping(value = "/show-reports")
    public String list(Model uiModel, Pageable pageable) {
        User currentUser = userService.obtainCurrentPrincipleUser();
        Page<Report> page = reportService.findAllReportsOfPersonsByAssignedInspector(currentUser, pageable);

        uiModel.addAttribute("page", page);
        uiModel.addAttribute("url", "/inspector/show-reports");

        return "inspector/show-reports";
    }
}


