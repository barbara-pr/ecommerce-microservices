package com.barbara.ms_pedido.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class PedidoController {
	@GetMapping("/boasVindas")
	public String boasVindas() {
		return "Essa é minha primeira mensagem nessa rota";
	}
}
