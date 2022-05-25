package it.uniroma3.diadia.comandi;

import uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando {
	
	static final public String NOME = "fine";
	static final public String MESSAGGIO_FINE = "Grazie di aver giocato!";
	
	public ComandoFine() {
		super(NOME, null);
	}
	
	/**
	 * Comando "Fine".
	 */
	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggioACapo(MESSAGGIO_FINE); // si desidera smettere
	}

}
