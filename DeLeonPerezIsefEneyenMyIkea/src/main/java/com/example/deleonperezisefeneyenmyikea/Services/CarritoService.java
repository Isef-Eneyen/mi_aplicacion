package com.example.deleonperezisefeneyenmyikea.Services;

import com.example.deleonperezisefeneyenmyikea.Models.Carrito_Producto;
import com.example.deleonperezisefeneyenmyikea.Models.Productoffer;
import com.example.deleonperezisefeneyenmyikea.Models.User;
import com.example.deleonperezisefeneyenmyikea.Repositories.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoService {
    @Autowired
    CarritoRepository carritoRepository;

    public List<Carrito_Producto> Carritos(){return carritoRepository.findAll();}
    public List<Carrito_Producto> carritoUser(User user){return carritoRepository.findByUser(user);}
    public Carrito_Producto ProductoCarrito(Integer idProduct){return carritoRepository.findById(idProduct).orElse(null);}
    public Carrito_Producto saveProductoCarrito(Carrito_Producto product){return carritoRepository.save(product);}
    public void removeProductoCarrito(Carrito_Producto product){carritoRepository.delete(product);}
    public void vaciarCarrito(){carritoRepository.deleteAll();}
}
