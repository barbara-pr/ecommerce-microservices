package com.barbara.ms_pedido.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	@PostMapping()
	public ResponseEntity<String> criar(){
		return ResponseEntity.ok("Pedido criado com sucesso!");
	}
	
	@PostMapping("/{id}/pagar")
	public ResponseEntity<String> pagar(@PathVariable Long id) {
		return ResponseEntity.ok("Pagamento do pedido " + id + " concluído!");
	}
	
	@PostMapping("/{id}/cancelar")
	public ResponseEntity<String> cancelar(@PathVariable Long id) {
		return ResponseEntity.ok("Cancelamento do pedido " + id + " concluído!");
	}
	
	@PostMapping("/{id}/estornar")
	public ResponseEntity<String> estornar(@PathVariable Long id) {
		return ResponseEntity.ok("Estorno do pedido " + id + " concluído!");
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<String> buscarPorId(@PathVariable Long id) {
	    return ResponseEntity.ok("Dados do pedido " + id);
	}

	@GetMapping
	public ResponseEntity<String> listar() {
	    return ResponseEntity.ok("Lista de pedidos");
	}

}
