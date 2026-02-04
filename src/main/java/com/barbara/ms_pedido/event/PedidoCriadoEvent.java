package com.barbara.ms_pedido.event;

public class PedidoCriadoEvent extends PedidoEvent {
    public PedidoCriadoEvent(Long pedidoId) {
        super(pedidoId);
    }
}