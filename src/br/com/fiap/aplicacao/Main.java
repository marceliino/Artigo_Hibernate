package br.com.fiap.aplicacao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.core.HibernateUtil;
import br.com.fiap.entity.Cliente;
import br.com.fiap.entity.Item;
import br.com.fiap.entity.Pedido;


public class Main {
	
	private static Transaction tx;

	public static void main(String[] args) {
		
		insere();
		listar();
		testeTransacao();
		testeCache();
		testeTransacaoConcorrente();
	}

	private static void testeTransacaoConcorrente() {
		
		System.out.println("Item descricao" + getItem(1).getDescricao());
		
		Item item1 = getItem(1);
		Item item2 = getItem(1);
		item1.setDescricao("Produtos para Pesca.");
		item2.setDescricao("Equipamento de Mergulho");
		
		System.out.println("Item1 descricao" + item1);
		System.out.println("Item2 descricao" + item2);
		
		atualiza(item1);
		atualiza(item2);
		
		item1 = getItem(1);
		item2 = getItem(1);
		
		System.out.println("Item1 descricao " + getItem(1).getDescricao());
		System.out.println("Item2 descricao " + getItem(1).getDescricao());
		
	}
	
	public static Item getItem(int id){
		Item item = new Item();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.load(item, id);
			session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return item;
	}
	
	private static void atualiza(Item item) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			System.out.println("Atualiza objeto)");
			
			session.beginTransaction();
			session.saveOrUpdate(item);
			session.getTransaction().commit();
			System.out.println("Atualizado");
			session.close();
		} catch (Exception e) {
			{    
	              tx.rollback();
	           }
	              session.close();
		}
	}

	@SuppressWarnings("unchecked")
	private static void testeCache() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("from Item");
		query.setCacheable(true);
		List<Item> item = query.list();
		System.out.println("Teste Cache: " + item.get(0).getDescricao());
		session.close();
		
	}

	private static void testeTransacao() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
		    //set transaction timeout de 3 segundos
			session.getTransaction().setTimeout(3);
			session.getTransaction().begin();

		    //operacao
			listar();

		    session.getTransaction().commit();
		}
		catch (RuntimeException e) {
			session.getTransaction().rollback();
		    throw e; // or display error message
		}
		finally {
			session.close();
		}
		
	}

	private static void insere() {
		System.out.println("Hibernate one to many (XML Mapping)");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Cliente cliente = new Cliente();
		cliente.setNome("Marcelo");
		cliente.setEndereco("Rua Marte 21");
		session.save(cliente);
		
		Pedido pedido = new Pedido();
		pedido.setProduto("Guitarra Jackson");
		pedido.setCliente(cliente);
		cliente.getPedidos().add(pedido);
		session.save(pedido);
		
		Item item = new Item();
		item.setDescricao("Instrumento Musical");
		item.setQuantidade(2);
		session.save(item);
		
		session.getTransaction().commit();
		System.out.println("Done");
	}

	@SuppressWarnings("unchecked")
	private static void listar() {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {   
			setTx(session.beginTransaction());
			Query q = session.createQuery("from Cliente");
			clientes = q.list();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		for(Cliente cliente : clientes) {
			System.err.println("Cliente nome: " + cliente.getNome());
		}
		
	}

	public static Transaction getTx() {
		return tx;
	}

	public static void setTx(Transaction tx) {
		Main.tx = tx;
	}
}