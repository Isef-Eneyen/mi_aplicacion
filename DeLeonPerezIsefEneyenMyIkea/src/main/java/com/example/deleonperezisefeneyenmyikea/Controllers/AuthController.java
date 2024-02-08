package com.example.deleonperezisefeneyenmyikea.Controllers;

import com.example.deleonperezisefeneyenmyikea.Services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController
{
    UserService userService;

    public AuthController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String Login()
    {
        return "/auth/login";
    }

    @GetMapping("/register")
    public String Register()
    {
        return "/auth/register";
    }

    @PostMapping("/register")
    public String Register(@RequestParam String name, @RequestParam String email, @RequestParam String password, Model model)
    {
        try
        {
            userService.saveUser(name,email,password);

            return "redirect:/login";
        }
        catch (Exception e)
        {
            model.addAttribute("error", "Error al registrar el usuario");
            return "auth/register";
        }
    }

    @GetMapping("/logout")
    public String Logout(HttpSession session)
    {
        session.invalidate();
        return "redirect:/";
    }
}
