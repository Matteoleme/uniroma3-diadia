package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.giocatore.Borsa;
import uniroma3.diadia.IO;
import uniroma3.diadia.Partita;

/**
 * attraverso la quale il giocatore può regalare un attrezzo al personaggio
 * presente nella stanza
 * 
 * @author biond
 *
 */
public class ComandoRegala extends AbstractComando {

	static final private String NOME = "Regala";
	private String nomeAttrezzo;
	
	public ComandoRegala() {
		super(NOME, null);
	}

	@Override
	public void esegui(Partita partita) {
		Borsa borsa = partita.getGiocatore().getBorsa();
		IO io = partita.getIO();
		if (borsa.hasAttrezzo(nomeAttrezzo)) {
			partita.getStanzaCorrente().getPersonaggio().riceviRegalo(borsa.getAttrezzo(nomeAttrezzo), partita);
			borsa.removeAttrezzo(nomeAttrezzo);
			return;

		} else if (nomeAttrezzo != null)
			io.mostraMessaggioACapo(nomeAttrezzo + " non presente");
		else
			io.mostraMessaggioACapo("Specificare attrezzo");
	}

}
