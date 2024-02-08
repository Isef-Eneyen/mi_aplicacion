package com.example.deleonperezisefeneyenmyikea.Controllers;

import com.example.deleonperezisefeneyenmyikea.Models.User;
import com.example.deleonperezisefeneyenmyikea.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.Collection;


@Controller
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@RequestMapping("/usuarios")
public class UserController
{
    @Autowired
    UserService userService;

    @GetMapping("")
    public String GetUsers(Model model, Authentication authentication)
    {
        Collection<User> users = userService.getUsers();
        Collection<User> noMyUser = new ArrayList<>();
        User myAdminUser = userService.GetUserEmail(authentication.getName());

        for(User user : users)
        {
            if(user != myAdminUser)
            {
                noMyUser.add(user);
            }
        }

        model.addAttribute("users", noMyUser);

        return "user/index";
    }

    @GetMapping("/eliminar/{id}")
    public String DeleteUser(@PathVariable int id, Authentication authentication)
    {
        User user = userService.GetUserId(id);
        User myAdminUser = userService.GetUserEmail(authentication.getName());

        if(user == myAdminUser)
        {
            return "user/error";
        }
        else
        {
            userService.DeleteUser(user);
        }

        return "redirect:/usuarios";
    }
}
