package com.example.deleonperezisefeneyenmyikea.Services;

import com.example.deleonperezisefeneyenmyikea.Models.Provincia;
import com.example.deleonperezisefeneyenmyikea.Repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaService
{
    @Autowired
    ProvinciaRepository provinciaRepository;

    public List<Provincia> provincias() {
        return provinciaRepository.findAll();
    }

    /*public Provincia provincia(Integer id) {return provinciaRepository.findById(id).orElse(null);}*/
}
