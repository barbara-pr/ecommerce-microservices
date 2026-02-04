package com.barbara.ms_pedido.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.barbara.ms_pedido.domain.Pedido;
import com.barbara.ms_pedido.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	private final PedidoService pedidoService;

	public PedidoController(PedidoService pedidoService) {
	    this.pedidoService = pedidoService;
	}
	
	@PostMapping
	public ResponseEntity<Pedido> criar(@RequestBody Pedido pedido) {
	    Pedido criado = pedidoService.criarPedido(pedido);
	    return ResponseEntity.ok(criado);
	}
	
	@PostMapping("/{id}/pagar")
	public ResponseEntity<Pedido> pagar(@PathVariable Long id) {
	    Pedido pedido = pedidoService.pagarPedido(id);
	    return ResponseEntity.ok(pedido);
	}

	@PostMapping("/{id}/cancelar")
	public ResponseEntity<Pedido> cancelar(@PathVariable Long id) {
	    Pedido pedido = pedidoService.cancelarPedido(id);
	    return ResponseEntity.ok(pedido);
	}
	
	@PostMapping("/{id}/estornar")
	public ResponseEntity<Pedido> estornar(@PathVariable Long id) {
	    Pedido pedido = pedidoService.estornarPedido(id);
	    return ResponseEntity.ok(pedido);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
	    return ResponseEntity.ok(pedidoService.buscarPorId(id));
	}

	@GetMapping
	public ResponseEntity<List<Pedido>> listar() {
	    return ResponseEntity.ok(pedidoService.listar());
	}
}
