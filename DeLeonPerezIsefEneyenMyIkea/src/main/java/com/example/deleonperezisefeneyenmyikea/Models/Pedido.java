package com.example.deleonperezisefeneyenmyikea.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;


@Entity
@Getter
@Setter
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id", nullable = false)
    private Integer id;

    @Column(name = "fecha_pedido", nullable = false)
    private Date fechaPedido;

    @ManyToMany
    @JoinTable(
            name = "pedido_productos",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Productoffer> productos;

    @Column(name = "precio_total")
    private Float precioTotal;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;



}


