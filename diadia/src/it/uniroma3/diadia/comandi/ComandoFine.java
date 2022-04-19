package it.uniroma3.diadia.comandi;

import uniroma3.diadia.Partita;

public class ComandoFine implements Comando {
	
	static final private String NOME = "Fine";
	/**
	 * Comando "Fine".
	 */
	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggioACapo("Grazie di aver giocato!"); // si desidera smettere
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
