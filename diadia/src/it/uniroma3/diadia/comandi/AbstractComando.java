package it.uniroma3.diadia.comandi;

import uniroma3.diadia.Partita;

public abstract class AbstractComando {

	private String nome;
	private String parametro;
	
	public AbstractComando(String nome, String parametro) {
		this.nome = nome;
		this.parametro = parametro;
	}
	
	public String getNome() {
		return this.nome;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	public String getParametro() {
		return this.parametro;
	}
	
	public abstract void esegui(Partita partita);
}
