package br.com.fiap.entity;

import java.util.HashSet;
import java.util.Set;

public class Cliente  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6846730435507404589L;
	
	private int id;
	private String nome;
	private String endereco;
	private Set<Pedido> pedidos = new HashSet<Pedido>(0);
	
	//getter, setter and constructor
	
	public Cliente(String nome, String endereco) {
		super();
		this.nome = nome;
		this.endereco = endereco;
	}

	public Cliente() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
}