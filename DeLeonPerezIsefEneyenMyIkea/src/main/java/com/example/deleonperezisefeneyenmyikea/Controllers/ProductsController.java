package com.example.deleonperezisefeneyenmyikea.Controllers;


import com.example.deleonperezisefeneyenmyikea.Models.Municipio;
import com.example.deleonperezisefeneyenmyikea.Models.Productoffer;
import com.example.deleonperezisefeneyenmyikea.Models.Provincia;
import com.example.deleonperezisefeneyenmyikea.Services.MunicipioService;
import com.example.deleonperezisefeneyenmyikea.Services.ProductofferService;
import com.example.deleonperezisefeneyenmyikea.Services.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@RequestMapping ("/productos")
public class ProductsController
{
    @Autowired
    ProductofferService productofferService;
    @Autowired
    MunicipioService municipioService;
    @Autowired
    ProvinciaService provinciaService;

    public static String ruta = "target/classes/static/images/";
    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    public String Products(Model model)
    {
        List<Productoffer> productos = productofferService.ListProducts();
        model.addAttribute("productos", productos);
        return "product/products";
    }

    @GetMapping("/detalles/{id}")
    @PreAuthorize("isAuthenticated()")
    public String Details(Model model,@PathVariable Integer id)
    {
        Productoffer product = productofferService.GetProduct(id);

        if(product == null)
        {
            return "product/errorProduct";
        }

        model.addAttribute("product", product);
        return "product/productDetails";
    }

    @GetMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public String Create(Model model)
    {
        List<Municipio> municipios = municipioService.municipios();
        List<Provincia> provincias = provinciaService.provincias();
        model.addAttribute("municipios", municipios);
        model.addAttribute("provincias", provincias);
        model.addAttribute("product", new Productoffer());
        return "product/createProduct";
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public String Create(@ModelAttribute("product") Productoffer product, @RequestParam("image") MultipartFile file) throws IOException
    {

        StringBuilder stringBuilder = new StringBuilder();
        Path path = Path.of(ruta + file.getOriginalFilename());
        stringBuilder.append(file.getOriginalFilename());
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        product.setProductPicture(file.getOriginalFilename());

        productofferService.SaverProduct(product);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public String editar(@PathVariable int id, Model model)
    {
        Productoffer producto = productofferService.GetProduct(id);
        List<Municipio> municipios = municipioService.municipios();
        List<Provincia> provincias = provinciaService.provincias();

        if(producto == null)
        {
            return "product/errorProduct";
        }

        model.addAttribute("municipios", municipios);
        model.addAttribute("provincias", provincias);
        model.addAttribute("producto", producto);
        return "product/edit";
    }

    @PostMapping("/editar")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public String editar(@ModelAttribute("product") Productoffer productActualizado, @RequestParam("image") MultipartFile file) throws IOException {

        System.out.println(productActualizado.getId());

        if(file != null && !file.isEmpty())
        {
            StringBuilder stringBuilder = new StringBuilder();
            Path path = Path.of(ruta + file.getOriginalFilename());
            stringBuilder.append(file.getOriginalFilename());
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            productActualizado.setProductPicture(file.getOriginalFilename());
        }

        productofferService.SaverProduct(productActualizado);
        return "redirect:/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id)
    {
        Productoffer product = productofferService.GetProduct(id);

        if(product == null)
        {
            return "product/errorProduct";
        }

        productofferService.delete(product);

        return "redirect:/productos";
    }
}
