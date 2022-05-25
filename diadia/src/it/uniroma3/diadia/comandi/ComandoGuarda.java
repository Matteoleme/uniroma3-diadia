package it.uniroma3.diadia.comandi;

import uniroma3.diadia.IO;
import uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando {
	
	static final public String NOME = "guarda";
	
	public ComandoGuarda() {
		super(NOME, null);
	}
	
	/**
	 * stampa le informazioni sulla stanza corrente e sullo stato della partita
	 */
	@Override
	public void esegui(Partita partita) {
		IO io = partita.getIO();
		io.mostraMessaggioACapo(partita.getStanzaCorrente().toString());	//stampa stanza corrente
		io.mostraMessaggioACapo("Cfu attuali " + partita.getGiocatore().getCfu());	//stampo cfu attuali
	}


}
