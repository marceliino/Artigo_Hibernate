package br.com.fiap.entity;

import java.io.Serializable;

public class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6622435274913120948L;
	
	private int id;
	private String descricao;
	private int quantidade;
	
	//getter, setter and constructor
	
	public Item() {
	}
	
	public Item(String descricao, int quantidade) {
		this.descricao = descricao;
		this.quantidade = quantidade;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}