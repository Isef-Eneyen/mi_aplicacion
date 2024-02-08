package com.example.deleonperezisefeneyenmyikea.Seeder;

import com.example.deleonperezisefeneyenmyikea.Models.Role;
import com.example.deleonperezisefeneyenmyikea.Models.User;
import com.example.deleonperezisefeneyenmyikea.Services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;

@Component
public class Seeder
{
    private final UserService userService;

    @Autowired
    public Seeder(UserService userService){
        this.userService = userService;
    }

    @PostConstruct
    public void seedDatabase() {
        if (userService.getUsers().isEmpty()){
            seedUsers();
        }
    }

    private void seedUsers(){

        Role userRole = userService.getRol("ROLE_USER");
        if(userRole == null){
            userRole = new Role("ROLE_USER");
            userService.SaveRole(userRole);
        }

        Role managerRole = userService.getRol("ROLE_MANAGER");
        if(managerRole == null){
            managerRole = new Role("ROLE_MANAGER");
            userService.SaveRole(managerRole);
        }

        Role adminRole = userService.getRol("ROLE_ADMIN");
        if(adminRole == null){
            adminRole = new Role("ROLE_ADMIN");
            userService.SaveRole(adminRole);
        }

        User user = userService.GetUserEmail("user@myikea.com");
        if(user == null)
        {
            user = new User();
            user.setUserName("USER");
            user.setEmail("user@myikea.com");
            user.setPassword("1234");
            user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
            userService.saveUserSeeder(user);
        }

        User manager = userService.GetUserEmail("manager@myikea.com");
        if(manager == null)
        {
            manager = new User();
            manager.setUserName("MANAGER");
            manager.setEmail("manager@myikea.com");
            manager.setPassword("1234");
            manager.setRoles(new HashSet<>(Collections.singletonList(managerRole)));
            userService.saveUserSeeder(manager);
        }

        User admin = userService.GetUserEmail("admin@myikea.com");
        if(admin == null)
        {
            admin = new User();
            admin.setUserName("ADMIN");
            admin.setEmail("admin@myikea.com");
            admin.setPassword("1234");
            admin.setRoles(new HashSet<>(Collections.singletonList(adminRole)));
            userService.saveUserSeeder(admin);
        }

        User admin2 = userService.GetUserEmail("admin2@myikea.com");
        if(admin2 == null)
        {
            admin2 = new User();
            admin2.setUserName("ADMIN2");
            admin2.setEmail("admin2@myikea.com");
            admin2.setPassword("1234");
            admin2.setRoles(new HashSet<>(Collections.singletonList(adminRole)));
            userService.saveUserSeeder(admin2);
        }
    }
}
