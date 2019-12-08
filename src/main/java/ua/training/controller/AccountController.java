package ua.training.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.training.entities.User;
import ua.training.services.SecurityService;
import ua.training.services.UserService;
import ua.training.util.UserValidator;

@Controller
public class AccountController {

    private final Logger LOG = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;


    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "common/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            LOG.info("reg. form had errors. redirecting");
            return "common/registration";
        }

        userService.registerUser(userForm);
        securityService.autoLoginAfterReg(userForm.getUsername(), userForm.getPasswordConfirm());

        LOG.info("user registered");
        return "redirect:/personal-cabinet";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "common/login";
    }

}
