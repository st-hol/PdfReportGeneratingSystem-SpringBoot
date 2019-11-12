package ua.training.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.training.entities.Report;
import ua.training.entities.TaxableItem;
import ua.training.entities.User;
import ua.training.services.ReportService;
import ua.training.services.TaxableItemService;
import ua.training.services.UserService;

import java.util.List;


@Controller
@RequestMapping(value = "/inspector")
public class InspectorController {

    private final Logger logger = LoggerFactory.getLogger(InspectorController.class);

    @Autowired
    private ReportService reportService;
    @Autowired
    private UserService userService;
    @Autowired
    private TaxableItemService taxableItemService;

    @GetMapping(value = "/personal-cabinet")
    public String personalCabinet() {
        return "inspector/inspector-base";
    }


    @GetMapping(value = "/check-report/{id}")
    public String checkReportForm(@PathVariable Long id, Model uiModel) {
        Report report = reportService.findById(id);
        uiModel.addAttribute("report", report);
        return "inspector/check-report";
    }

    @PostMapping(value = "/check-report")
    public String checkReport(@RequestParam Long id, Report report) {
        reportService.check(id, report);
        return "redirect:/inspector/show-reports";
    }


    @GetMapping(value = "/set-taxable")
    public String setTaxableForm(Model uiModel) {
        List<TaxableItem> taxableItems = taxableItemService.findAll();
        uiModel.addAttribute("items", taxableItems);
        List<User> users = userService.findAll();
        uiModel.addAttribute("users", users);
        return "inspector/set-taxable";
    }

    @PostMapping(value = "/set-taxable")
    public String setTaxable(@RequestParam Long idItem, @RequestParam Long idUser) {
        User user = userService.findById(idUser);
        user.getTaxableItems().add(taxableItemService.findById(idItem));
        userService.save(user);
        return "redirect:/inspector/set-taxable";
    }


    @GetMapping(value = "/show-reports")
    public String list(Model uiModel,
//            @PageableDefault(sort = { "id_report" }, direction = Sort.Direction.DESC) Pageable pageable)
                       Pageable pageable) {
        User currentUser = userService.obtainCurrentPrincipleUser();
        Page<Report> page = reportService.findAllReportsOfPersonsByAssignedInspector(currentUser, pageable);

        uiModel.addAttribute("page", page);
        uiModel.addAttribute("url", "/inspector/show-reports");

        return "inspector/show-reports";
    }

//    //todo pagination
//    @GetMapping(value = "/show-reports")
//    public String list(Model uiModel) {
//        logger.info("Listing reports");
//        List<Report> reports = reportService.findAllReportsOfPersonsByAssignedInspector(userService
//                .obtainCurrentPrincipleUser());
//        uiModel.addAttribute("reports", reports);
//        logger.info("No. of rec.: " + reports.size());
//        return "inspector/show-reports";
//    }
}
