package com.barbara.ms_pedido.event;

public class PedidoCanceladoEvent extends PedidoEvent {
    public PedidoCanceladoEvent(Long pedidoId) {
        super(pedidoId);
    }
}
