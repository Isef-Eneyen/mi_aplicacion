package com.example.deleonperezisefeneyenmyikea.Services;

import com.example.deleonperezisefeneyenmyikea.Models.Municipio;
import com.example.deleonperezisefeneyenmyikea.Repositories.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipioService {
    @Autowired
    MunicipioRepository municipioRepository;

    public List<Municipio> municipios() {
        return municipioRepository.findAll();
    }

    /*public Municipio municipio(Integer id) {return municipioRepository.findById(id).orElse(null);}*/

}