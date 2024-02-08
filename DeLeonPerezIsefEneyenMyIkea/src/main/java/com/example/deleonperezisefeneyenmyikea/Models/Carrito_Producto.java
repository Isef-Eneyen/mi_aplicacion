package com.example.deleonperezisefeneyenmyikea.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Carrito_Producto")

public class Carrito_Producto
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id",nullable = false)
    private Short idCarrito;

    @ManyToOne
    @JoinColumn(name = "product")
    private Productoffer product;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
