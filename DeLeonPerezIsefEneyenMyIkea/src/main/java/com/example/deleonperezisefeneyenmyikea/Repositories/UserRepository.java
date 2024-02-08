package com.example.deleonperezisefeneyenmyikea.Repositories;

import com.example.deleonperezisefeneyenmyikea.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
    User findByEmail(String email);
}
