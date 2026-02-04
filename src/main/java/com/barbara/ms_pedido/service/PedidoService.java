package com.barbara.ms_pedido.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.barbara.ms_pedido.domain.Pedido;
import com.barbara.ms_pedido.domain.PedidoStatus;
import com.barbara.ms_pedido.event.PedidoCanceladoEvent;
import com.barbara.ms_pedido.event.PedidoCriadoEvent;
import com.barbara.ms_pedido.event.PedidoEstornadoEvent;
import com.barbara.ms_pedido.event.PedidoEventPublisher;
import com.barbara.ms_pedido.event.PedidoPagoEvent;
import com.barbara.ms_pedido.repository.PedidoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoEventPublisher eventPublisher;

    public PedidoService(PedidoRepository pedidoRepository,
    					PedidoEventPublisher eventPublisher) {
        this.pedidoRepository = pedidoRepository;
        this.eventPublisher = eventPublisher;
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
        
        Pedido salvo = pedidoRepository.save(pedido);
        
        eventPublisher.publish(new PedidoCriadoEvent(salvo.getId())); // só publica se salvou no banco

        return salvo;
    }
    
    public Pedido pagarPedido(Long pedidoId) {

        Pedido pedido = pedidoRepository.findById(pedidoId)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (pedido.getStatus() == PedidoStatus.PAGO) {
            throw new IllegalStateException("Pedido já foi pago");
        }

        pedido.setStatus(PedidoStatus.PAGO);
        
        Pedido salvo = pedidoRepository.save(pedido);
        
        eventPublisher.publish(new PedidoPagoEvent(salvo.getId()));

        return salvo;
    }

    public Pedido cancelarPedido(Long id) {

        Pedido pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (pedido.getStatus() == PedidoStatus.PAGO) {
            throw new IllegalStateException("Pedido pago não pode ser cancelado");
        }

        pedido.setStatus(PedidoStatus.CANCELADO);
        
        Pedido salvo = pedidoRepository.save(pedido);
        
        eventPublisher.publish(new PedidoCanceladoEvent(salvo.getId()));

        return salvo;
    }

    public Pedido estornarPedido(Long id) {

        Pedido pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (pedido.getStatus() != PedidoStatus.PAGO) {
            throw new IllegalStateException("Somente pedidos pagos podem ser estornados");
        }

        pedido.setStatus(PedidoStatus.ESTORNADO);
        
        Pedido salvo = pedidoRepository.save(pedido);
        
        eventPublisher.publish(new PedidoEstornadoEvent(salvo.getId()));

        return salvo;
    }
    
    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

}
