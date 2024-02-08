package com.example.deleonperezisefeneyenmyikea.Services;

import com.example.deleonperezisefeneyenmyikea.Models.Productoffer;
import com.example.deleonperezisefeneyenmyikea.Repositories.ProductofferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductofferService
{
    @Autowired
    ProductofferRepository productofferRepository;

    public Productoffer SaverProduct(Productoffer producto) {
        return productofferRepository.save(producto);
    }

    public void delete(Productoffer product){productofferRepository.delete(product);}
    public List<Productoffer> ListProducts() {
        return productofferRepository.findAll();
    }

    public Productoffer GetProduct(Integer id) {
        return productofferRepository.findById(id).orElse(null);
    }
}
