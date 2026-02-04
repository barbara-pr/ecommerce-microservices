package com.barbara.ms_pedido.event;

import java.time.LocalDateTime;

public abstract class PedidoEvent {

    private Long pedidoId;
    private LocalDateTime dataEvento;

    protected PedidoEvent(Long pedidoId) {
        this.pedidoId = pedidoId;
        this.dataEvento = LocalDateTime.now();
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public LocalDateTime getDataEvento() {
        return dataEvento;
    }
}