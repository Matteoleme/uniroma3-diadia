package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ambienti.Stanza;
import uniroma3.diadia.IO;
import uniroma3.diadia.Partita;

public class ComandoVai extends AbstractComando {
	

	static final private String NOME = "Vai";
	
	private String direzione;

	public ComandoVai() {
		super(NOME, null);
	}
	
	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra e ne stampa il
	 * nome, altrimenti stampa un messaggio di errore
	 */
	@Override
	public void esegui(Partita partita) {
		IO io = partita.getIO();
		if (this.direzione == null) {
			io.mostraMessaggioACapo("Dove vuoi andare ?");
			return;
		}
		Stanza prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(this.direzione);
		if (prossimaStanza == null)
			io.mostraMessaggioACapo("Direzione inesistente");
		else {
			partita.setStanzaCorrente(prossimaStanza);
			int cfu = partita.getGiocatore().getCfu()-1;
			partita.getGiocatore().setCfu(cfu);
		}
		io.mostraMessaggioACapo(partita.getStanzaCorrente().getDescrizione());
	}

	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}

	@Override
	public String getParametro() {
		return this.direzione;
	}
	
}