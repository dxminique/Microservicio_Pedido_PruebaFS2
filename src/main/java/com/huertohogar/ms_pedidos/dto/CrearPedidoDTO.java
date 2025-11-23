package com.huertohogar.ms_pedidos.dto;

import java.util.List;

public class CrearPedidoDTO {

    private String emailCliente;
    private List<ItemPedidoDTO> items;

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public List<ItemPedidoDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemPedidoDTO> items) {
        this.items = items;
    }
}