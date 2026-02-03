package com.barbara.ms_pedido.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.barbara.ms_pedido.domain.Pedido;
import com.barbara.ms_pedido.domain.PedidoStatus;
import com.barbara.ms_pedido.repository.PedidoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido criarPedido(Pedido pedido) {

        if (pedido.getValor() <= 0) {
            throw new IllegalArgumentException("Valor do pedido deve ser maior que zero");
        }
        
        if (pedido.getDescricao() == null || pedido.getDescricao().isBlank()) {
            throw new IllegalArgumentException("Descrição do pedido é obrigatória");
        }

        pedido.setStatus(PedidoStatus.CRIADO);
        
        pedido.setDataCriacao(LocalDateTime.now());

        return pedidoRepository.save(pedido);
    }
    
    public Pedido pagarPedido(Long pedidoId) {

        Pedido pedido = pedidoRepository.findById(pedidoId)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (pedido.getStatus() == PedidoStatus.PAGO) {
            throw new IllegalStateException("Pedido já foi pago");
        }

        pedido.setStatus(PedidoStatus.PAGO);

        return pedidoRepository.save(pedido);
    }

    public Pedido cancelarPedido(Long id) {

        Pedido pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (pedido.getStatus() == PedidoStatus.PAGO) {
            throw new IllegalStateException("Pedido pago não pode ser cancelado");
        }

        pedido.setStatus(PedidoStatus.CANCELADO);

        return pedidoRepository.save(pedido);
    }

    public Pedido estornarPedido(Long id) {

        Pedido pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (pedido.getStatus() != PedidoStatus.PAGO) {
            throw new IllegalStateException("Somente pedidos pagos podem ser estornados");
        }

        pedido.setStatus(PedidoStatus.ESTORNADO);

        return pedidoRepository.save(pedido);
    }
}
