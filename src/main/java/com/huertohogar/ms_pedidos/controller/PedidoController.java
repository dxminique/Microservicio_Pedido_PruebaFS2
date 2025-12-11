package com.huertohogar.ms_pedidos.controller;

import com.huertohogar.ms_pedidos.dto.CrearPedidoDTO;
import com.huertohogar.ms_pedidos.model.Pedido;
import com.huertohogar.ms_pedidos.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "pedido-controller",
        description = "Operaciones para gestionar pedidos de HuertoHogar"
)
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Operation(
            summary = "Listar todos los pedidos",
            description = "Obtiene la lista de pedidos registrados en el sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de pedidos obtenida correctamente")
    })
    @GetMapping
    public List<Pedido> obtenerPedidos() {
        return pedidoService.listar();
    }

    @Operation(
            summary = "Crear un pedido",
            description = "Registra un nuevo pedido sin lógica de confirmación ni actualización de stock."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedido creado correctamente")
    })
    @PostMapping
    public Pedido crearPedido(@RequestBody Pedido pedido) {
        return pedidoService.guardar(pedido);
    }

    @Operation(
            summary = "Confirmar un pedido",
            description = "Registra un pedido a partir del DTO y descuenta el stock de los productos involucrados."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedido registrado y stock actualizado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en el pedido")
    })
    @PostMapping("/confirmar")
    public ResponseEntity<?> confirmarPedido(
            @Parameter(description = "Objeto con los datos necesarios para confirmar el pedido")
            @RequestBody CrearPedidoDTO dto
    ) {
        pedidoService.confirmarPedido(dto);
        return ResponseEntity.ok("Pedido registrado y stock actualizado");
    }
}
