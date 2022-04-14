package it.uniroma3.diadia.comandi;

import uniroma3.diadia.Partita;

public class ComandoFine implements Comando {

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

}
