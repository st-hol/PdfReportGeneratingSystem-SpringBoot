package ua.training.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.training.entities.Report;
import ua.training.entities.User;
import ua.training.services.ReportService;
import ua.training.services.UserService;


@Controller
@RequestMapping(value = "/inspector")
public class InspectorController {

    private static final Logger LOG = LoggerFactory.getLogger(InspectorController.class);

    @Autowired
    private ReportService reportService;
    @Autowired
    private UserService userService;


    @GetMapping(value = "/personal-cabinet")
    public String personalCabinet() {
        return "inspector/inspector-base";
    }


    @PostMapping(value = "/check-report")
    public String checkReport(@RequestParam Long id, Report report) {
        reportService.check(id, report);
        return "redirect:/inspector/show-reports";
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


