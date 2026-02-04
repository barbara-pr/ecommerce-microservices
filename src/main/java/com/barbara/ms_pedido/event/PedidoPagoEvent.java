package com.barbara.ms_pedido.event;

public class PedidoPagoEvent extends PedidoEvent {
    public PedidoPagoEvent(Long pedidoId) {
        super(pedidoId);
    }
}