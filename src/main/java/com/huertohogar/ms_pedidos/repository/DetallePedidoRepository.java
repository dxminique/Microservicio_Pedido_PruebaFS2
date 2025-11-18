package com.huertohogar.ms_pedidos.repository;

import com.huertohogar.ms_pedidos.model.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {
}
