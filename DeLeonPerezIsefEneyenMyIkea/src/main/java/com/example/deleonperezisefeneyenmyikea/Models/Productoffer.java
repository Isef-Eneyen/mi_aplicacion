package com.example.deleonperezisefeneyenmyikea.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "productoffer")
public class Productoffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Integer id;

    @Size(max = 512)
    @NotNull
    @Column(name = "product_name", nullable = false, length = 512)
    private String productName;

    @Column(name = "product_price")
    private Float productPrice;

    @Size(max = 512)
    @Column(name = "product_picture", length = 512)
    private String productPicture;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_municipio", nullable = false)
    private Municipio idMunicipio;

    @NotNull
    @Column(name = "product_stock", nullable = false)
    private Integer productStock;
}