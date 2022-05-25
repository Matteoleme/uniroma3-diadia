package it.uniroma3.diadia.comandi;

import uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {
	
	static final private String NOME = "NonValido";
	
	public ComandoNonValido() {
		super(NOME, null);
	}
	
	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggioACapo("Comando inserito non riconosciuto");
	}

}
