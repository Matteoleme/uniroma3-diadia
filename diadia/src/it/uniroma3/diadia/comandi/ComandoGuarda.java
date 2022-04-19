package it.uniroma3.diadia.comandi;

import uniroma3.diadia.IOConsole;
import uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {
	
	static final private String NOME = "Guarda";
	/**
	 * stampa le informazioni sulla stanza corrente e sullo stato della partita
	 */
	@Override
	public void esegui(Partita partita) {
		IOConsole io = partita.getIO();
		io.mostraMessaggioACapo(partita.getStanzaCorrente().toString());	//stampa stanza corrente
		io.mostraMessaggioACapo("Cfu attuali " + partita.getGiocatore().getCfu());	//stampo cfu attuali
	}

	@Override
	public void setParametro(String parametro) {
		// niente

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
