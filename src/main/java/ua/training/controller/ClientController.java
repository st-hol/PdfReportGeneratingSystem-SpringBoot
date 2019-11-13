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

import java.util.Calendar;

@Controller
@RequestMapping(value = "/client")
public class ClientController {

    private static final Logger LOG = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private ReportService reportService;

    @GetMapping(value = "/personal-cabinet")
    public String personalCabinet() {
        return "client/client-base";
    }

    @GetMapping(value = "/make-report")
    public String createReportForm(Model uiModel) {

        Report report = new Report();
        uiModel.addAttribute("report", report);
        return "client/make-report";
    }

    @GetMapping(value = "/edit-report/{id}")
    public String editReportForm(@PathVariable Long id, Model uiModel) {
        Report report = reportService.findById(id);
        uiModel.addAttribute("report", report);
        return "client/edit-report";
    }

    @PostMapping(value = "/make-report")
    public String saveReport(@ModelAttribute("report") Report report) {
        User loggedInUser = userService.obtainCurrentPrincipleUser();
        report.setPerson(loggedInUser);
        report.setCompletionTime(Calendar.getInstance().getTime());
        report.setTotalAmountOfProperty(9099999);
        reportService.save(report);
        return "redirect:/client/make-report";
    }

    @PostMapping(value = "/edit-report")
    public String editReport(@RequestParam Long id, Report report) {
        reportService.edit(id, report);
        return "redirect:/client/show-reports";
    }



    @GetMapping(value = "/show-reports")
    public String list(Model uiModel, Pageable pageable) {
        User currentUser = userService.obtainCurrentPrincipleUser();
        Page<Report> page = reportService.findAllByPerson(currentUser, pageable);

        uiModel.addAttribute("page", page);
        uiModel.addAttribute("url", "/client/show-reports");

        return "client/show-reports";
    }



}
