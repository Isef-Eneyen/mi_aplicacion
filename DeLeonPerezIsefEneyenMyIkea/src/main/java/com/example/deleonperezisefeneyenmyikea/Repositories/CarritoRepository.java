package com.example.deleonperezisefeneyenmyikea.Repositories;

import com.example.deleonperezisefeneyenmyikea.Models.Carrito_Producto;
import com.example.deleonperezisefeneyenmyikea.Models.Role;
import com.example.deleonperezisefeneyenmyikea.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarritoRepository extends JpaRepository<Carrito_Producto,Integer>
{
    List<Carrito_Producto> findByUser(User user);
}
