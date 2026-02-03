package com.barbara.ms_pedido.domain;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_pedidos")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String descricao;
	
	private Double valor;
	
	@Enumerated(EnumType.STRING)
	private PedidoStatus status;
	
	private LocalDateTime dataCriacao;
	
	public Pedido() {}
	
	public Pedido(String descricao, Double valor, PedidoStatus status, LocalDateTime dataCriacao) {
		this.descricao = descricao;
		this.valor= valor;
		this.status = status;
		this.dataCriacao = dataCriacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public PedidoStatus getStatus() {
		return status;
	}

	public void setStatus(PedidoStatus status) {
		this.status = status;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
}
