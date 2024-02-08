package com.example.deleonperezisefeneyenmyikea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


//El extends también es de dpl
@SpringBootApplication
public class DeLeonPerezIsefEneyenMyIkeaApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(DeLeonPerezIsefEneyenMyIkeaApplication.class, args);
    }

    //Esto es para la actividad de dpl, sirve para desplegar
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder aplicacion)
    {
        return aplicacion.sources(DeLeonPerezIsefEneyenMyIkeaApplication.class);
    }
}
