package ua.training.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/error")
public class ErrorController {

    private final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @GetMapping(value = "/403")
    public String personalCabinet() {
        logger.info("Attempt of unlawful action was prevented. Access denied ");
        return "common/error/403";
    }



}
