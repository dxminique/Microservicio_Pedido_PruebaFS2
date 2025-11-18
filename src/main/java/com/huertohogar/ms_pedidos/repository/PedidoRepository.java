package com.huertohogar.ms_pedidos.repository;
import com.huertohogar.ms_pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}