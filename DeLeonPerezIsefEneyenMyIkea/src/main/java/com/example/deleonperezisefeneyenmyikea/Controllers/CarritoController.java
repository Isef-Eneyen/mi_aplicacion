package com.example.deleonperezisefeneyenmyikea.Controllers;

import com.example.deleonperezisefeneyenmyikea.Models.Carrito_Producto;
import com.example.deleonperezisefeneyenmyikea.Models.Productoffer;
import com.example.deleonperezisefeneyenmyikea.Models.User;
import com.example.deleonperezisefeneyenmyikea.Services.UserService;
import org.springframework.security.core.Authentication;
import com.example.deleonperezisefeneyenmyikea.Services.CarritoService;
import com.example.deleonperezisefeneyenmyikea.Services.ProductofferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/carrito")
@PreAuthorize("hasRole('ROLE_USER')")
public class CarritoController
{
    @Autowired
    CarritoService carritoService;
    @Autowired
    ProductofferService productofferService;
    @Autowired
    UserService userService;

    @GetMapping("")
    public String Comprar(Model model, Authentication authentication)
    {
        User user = userService.GetUserEmail(authentication.getName());
        List<Carrito_Producto> carritoProductoList = user.getCarritos();
        double precioTotal = 0;

        for(Carrito_Producto producto : carritoProductoList)
        {
            precioTotal += producto.getProduct().getProductPrice();
        }

        model.addAttribute("carrito", carritoProductoList);
        model.addAttribute("precioTotal", precioTotal);

        return "carrito/carrito";
    }

    @GetMapping("/añadir/{id}")
    public String AddProduct(@PathVariable Integer id, Authentication authentication)
    {
        Carrito_Producto carrito = new Carrito_Producto();
        Productoffer product = productofferService.GetProduct(id);
        User user = userService.GetUserEmail(authentication.getName());

        carrito.setProduct(product);
        carrito.setUser(user);

        carritoService.saveProductoCarrito(carrito);

        return "redirect:/carrito";
    }

    @GetMapping("/borrar/{id}")
    public String DeleteProduct(@PathVariable Integer id)
    {
        Carrito_Producto product = carritoService.ProductoCarrito(id);
        carritoService.removeProductoCarrito(product);

        return "redirect:/carrito";
    }

}
