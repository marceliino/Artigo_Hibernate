package br.com.fiap.entity;

import java.io.Serializable;

public class Pedido implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6069282091275299231L;
	
	private int id;
	private String produto;
	private Cliente cliente;
	
	//getter, setter and constructor
	
	public Pedido() {
		super();
	}
	public Pedido(Cliente cliente, String produto) {
		super();
		this.cliente = cliente;
		this.produto = produto;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
}