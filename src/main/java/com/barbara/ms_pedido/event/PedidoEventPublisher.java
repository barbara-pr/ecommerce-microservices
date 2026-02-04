package com.barbara.ms_pedido.event;

import org.springframework.stereotype.Component;

@Component
public class PedidoEventPublisher {

    public void publish(PedidoEvent event) {
        System.out.println(
            "Evento publicado: " + event.getClass().getSimpleName() +
            " | Pedido ID: " + event.getPedidoId()
        );
    }
}
