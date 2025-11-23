package com.huertohogar.ms_pedidos.service;

import com.huertohogar.ms_pedidos.dto.CrearPedidoDTO;
import com.huertohogar.ms_pedidos.dto.ItemPedidoDTO;
import com.huertohogar.ms_pedidos.model.DetallePedido;
import com.huertohogar.ms_pedidos.model.Pedido;
import com.huertohogar.ms_pedidos.repository.DetallePedidoRepository;
import com.huertohogar.ms_pedidos.repository.PedidoRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final DetallePedidoRepository detallePedidoRepository;
    private final RestTemplate restTemplate;

    public PedidoService(PedidoRepository pedidoRepository,
                         DetallePedidoRepository detallePedidoRepository,
                         RestTemplate restTemplate) {

        this.pedidoRepository = pedidoRepository;
        this.detallePedidoRepository = detallePedidoRepository;
        this.restTemplate = restTemplate;
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido guardar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void confirmarPedido(CrearPedidoDTO dto) {

        // 1. Crear pedido
        Pedido pedido = new Pedido();
        pedido.setEmailCliente(dto.getEmailCliente());
        pedido.setFecha(LocalDateTime.now());


        pedidoRepository.save(pedido);

        // 2. Crear detalles + descontar stock
        for (ItemPedidoDTO item : dto.getItems()) {

            DetallePedido det = new DetallePedido();
            det.setPedido(pedido);
            det.setIdProducto(item.getIdProducto());
            det.setCantidad(item.getCantidad());

            detallePedidoRepository.save(det);

            // 3. Llamar a ms-productos
            String url = "http://localhost:8082/api/productos/"
                    + item.getIdProducto()
                    + "/descontarStock?cantidad=" + item.getCantidad();

            restTemplate.put(url, null);
        }
    }
}
