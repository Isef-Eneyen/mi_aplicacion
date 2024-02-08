package com.example.deleonperezisefeneyenmyikea.Controllers;

import com.example.deleonperezisefeneyenmyikea.Models.Carrito_Producto;
import com.example.deleonperezisefeneyenmyikea.Models.Productoffer;
import com.example.deleonperezisefeneyenmyikea.Models.User;
import com.example.deleonperezisefeneyenmyikea.Services.CarritoService;
import com.example.deleonperezisefeneyenmyikea.Services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.example.deleonperezisefeneyenmyikea.Models.Pedido;
import com.example.deleonperezisefeneyenmyikea.Services.PedidoService;

import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/pedidos")
@PreAuthorize("hasRole('ROLE_USER')")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private CarritoService carritoService;
    @Autowired
    UserService userService;

    @GetMapping("")
    public String listarPedidos(Model model, Authentication authentication)
    {
        User user = userService.GetUserEmail(authentication.getName());
        List<Pedido> pedidos = user.getPedidos();
        model.addAttribute("pedidos", pedidos);
        return "pedido/pedidos";
    }

    @GetMapping("/details/{id}")
    public String verDetallesPedido(@PathVariable("id") Integer pedidoId, Model model) {
        Pedido pedido = pedidoService.GetPedido(pedidoId);
        model.addAttribute("pedido", pedido);
        return "pedido/detalles";
    }

    @GetMapping("/finalizar")
    public String finalizarPedido(Model model, Authentication authentication)
    {
        User user = userService.GetUserEmail(authentication.getName());
        List<Carrito_Producto> productosEnCarrito = user.getCarritos();
        float precioTotal = 0;
        List<Productoffer> productosDelPedido = new ArrayList<>();

        for(Carrito_Producto productoCarrito: productosEnCarrito)
        {
            precioTotal += productoCarrito.getProduct().getProductPrice();
            productosDelPedido.add(productoCarrito.getProduct());
        }

        Pedido nuevoPedido = new Pedido();

        nuevoPedido.setProductos(productosDelPedido);
        nuevoPedido.setFechaPedido(new Date());
        nuevoPedido.setPrecioTotal(precioTotal);
        nuevoPedido.setUser(user);
        user.setCarritos(new ArrayList<>());

        carritoService.vaciarCarrito();
        userService.SaveUserChange(user);
        pedidoService.Save(nuevoPedido);

        model.addAttribute("pedido", nuevoPedido);
        return "redirect:/pedidos";
    }
}