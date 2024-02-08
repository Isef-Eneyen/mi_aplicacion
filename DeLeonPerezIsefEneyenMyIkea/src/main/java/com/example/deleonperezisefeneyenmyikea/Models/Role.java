package com.example.deleonperezisefeneyenmyikea.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Roles")
public class Role
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoleId", nullable = false)
    private Long roleId;

    @Column(name = "Role", unique = true)
    private String role;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users = new HashSet<>();
    public Role(String role){
        this.role = role;
    }
}
