package it.uniroma3.diadia.comandi;

import uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {
	
	@Override
	public void esegui(Partita partita) {
		partita.getIO().mostraMessaggioACapo("Comando inserito non riconosciuto");
	}

	@Override
	public void setParametro(String parametro) {
		// niente
	}

}
