package com.example.deleonperezisefeneyenmyikea.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.deleonperezisefeneyenmyikea.Models.Pedido;
import com.example.deleonperezisefeneyenmyikea.Repositories.PedidoRepository;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    public List<Pedido> ListPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido GetPedido(Integer id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public void Save(Pedido pedido) {
        pedidoRepository.save(pedido);
    }
}


