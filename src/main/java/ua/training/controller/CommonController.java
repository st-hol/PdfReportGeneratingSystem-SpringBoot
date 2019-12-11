package ua.training.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.training.entities.Role;
import ua.training.entities.User;
import ua.training.services.RoleService;
import ua.training.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class CommonController {

    private static final Logger LOG = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "common/welcome";
    }


    @GetMapping(value = "/personal-cabinet")
    public String personalCabinet(Principal principal) {
        if (principal == null){
            throw new UnsupportedOperationException("No user is authorized. Can't go to personal cabinet.");
        }

        User loggedInUser = userService.findByUsername(principal.getName());
        List<Role> userRoles = roleService.findAllByUser(loggedInUser);

        //todo refactor this
        Role inspectorRole = roleService.findById(0L);
        Role clientRole = roleService.findById(1L);

        if (userRoles.contains(inspectorRole)) {
            return "redirect:/inspector/personal-cabinet";
        } else if (userRoles.contains(clientRole)) {
            return "redirect:/client/personal-cabinet";
        } else {
            return "redirect:/";
        }

    }


}
