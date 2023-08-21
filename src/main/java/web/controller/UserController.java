package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
            model.addAttribute("users", userService.getAllUsers());
            System.out.println("Returning 'all' view"); // Временная строка для логирования
            return "all";
    }
    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("users", userService.getUserById(id));
        return "one";
    }
    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add";

    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user){
        userService.addUser(user);
        return "redirect:/users";

    }
}
