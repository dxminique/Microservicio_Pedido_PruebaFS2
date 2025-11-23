package com.huertohogar.ms_pedidos.controller;

import com.huertohogar.ms_pedidos.dto.CrearPedidoDTO;
import com.huertohogar.ms_pedidos.model.Pedido;
import com.huertohogar.ms_pedidos.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<Pedido> obtenerPedidos() {
        return pedidoService.listar();
    }

    @PostMapping
    public Pedido crearPedido(@RequestBody Pedido pedido) {
        return pedidoService.guardar(pedido);
    }

    @PostMapping("/confirmar")
    public ResponseEntity<?> confirmarPedido(@RequestBody CrearPedidoDTO dto) {
        pedidoService.confirmarPedido(dto);
        return ResponseEntity.ok("Pedido registrado y stock actualizado");
    }
}
