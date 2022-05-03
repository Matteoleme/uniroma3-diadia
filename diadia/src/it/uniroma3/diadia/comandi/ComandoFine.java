package it.uniroma3.diadia.comandi;

import uniroma3.diadia.Partita;

public class ComandoFine implements Comando {
	
	static final public String NOME = "fine";
	static final public String MESSAGGIO_FINE = "Grazie di aver giocato!";
	/**
	 * Comando "Fine".
	 */
	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggioACapo(MESSAGGIO_FINE); // si desidera smettere
	}

	@Override
	public void setParametro(String parametro) {
		//niente
	}

	@Override
	public String getNome() {
		return NOME;
	}

	@Override
	public String getParametro() {
		return null;
	}

}
