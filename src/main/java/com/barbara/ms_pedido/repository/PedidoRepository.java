package com.barbara.ms_pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.barbara.ms_pedido.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}