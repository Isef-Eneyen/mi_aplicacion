package com.example.deleonperezisefeneyenmyikea.Repositories;

import com.example.deleonperezisefeneyenmyikea.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleReposictory extends JpaRepository<Role, Long>
{
    Role findByRole(String role);
}
