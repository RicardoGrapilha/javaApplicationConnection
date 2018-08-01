package br.com.model.bean;

public class Usuario {

	private int id;
	private String nome;
	private String sobre_nome;
	
	public Usuario() {
		super();
		this.id = 0;
		this.nome = "";
		this.sobre_nome = "";
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
	public String getSobre_nome() {
		return sobre_nome;
	}
	public void setSobre_nome(String sobre_nome) {
		this.sobre_nome = sobre_nome;
	}
	
	
}
